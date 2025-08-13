# 회원 정보 수정 페이지(`update.js`) 문제 해결 가이드

안녕하세요! 회원 정보 수정 페이지에서 기존 값이 `undefined`로 나오는 문제에 대해 분석하고, 해결 방법을 단계별로 안내해 드리겠습니다.

## 1. 문제 원인: "데이터를 주세요" 요청을 받는 사람이 없어요!

현재 `update.js`는 페이지가 열리자마자 `GET /assessment/update?custno=...` 라는 주소로 "이 회원 정보 좀 주세요" 라는 요청을 보냅니다.

하지만 `MemberController.java`를 보면 `/assessment/update` 주소에 대해서는 `@PutMapping`만 있고, `@GetMapping`이 없습니다.

- `@GetMapping`: "데이터를 달라"는 `GET` 요청을 처리합니다.
- `@PutMapping`: "데이터를 수정해달라"는 `PUT` 요청을 처리합니다.

즉, **자바스크립트는 데이터를 달라고 요청하는데, 스프링 컨트롤러에는 그 요청을 받아서 처리해 줄 메소드가 없는 상황**입니다. 따라서 자바스크립트는 아무런 데이터도 받지 못하고, `data` 객체는 비어있게 되어 모든 `input`에 `undefined`가 표시되는 것입니다.

## 2. 해결 방안: "데이터를 주세요" 요청을 처리하는 창구 만들기

문제를 해결하려면 다음의 흐름을 완성해야 합니다.

1.  **(Spring)** 특정 회원번호(`custno`)를 받아서 회원 정보를 찾아주는 로직을 **DAO, Service, Controller**에 각각 추가합니다.
2.  **(JavaScript)** 새로 만든 Spring의 "창구"(Controller 메소드)를 향해 `fetch` 요청을 보냅니다.
3.  **(JavaScript)** 응답으로 받은 회원 정보를 각 `input`에 채워 넣습니다.

## 3. 단계별 해결 방법

아래 순서대로 코드를 추가하고 수정해 보세요.

### 1단계: DAO에 특정 회원 조회 메소드 추가 (`MemberDao.java`)

`custno`를 이용해 `member_tbl_02` 테이블에서 특정 회원 한 명의 정보를 조회하는 SQL을 실행하는 메소드를 만듭니다.

```java
// MemberDao.java 파일에 아래 내용을 추가하세요.

// 5. 개별 회원 조회
public MemberDto getMember(int custno){
    MemberDto memberDto = null; // 미리 null로 선언
    try{
        String sql = "select * from member_tbl_02 where custno = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, custno);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            memberDto = new MemberDto(); // 결과가 있으면 객체 생성
            memberDto.setCustno(rs.getInt("custno"));
            memberDto.setCustname(rs.getString("custname"));
            memberDto.setPhone(rs.getString("phone"));
            memberDto.setAddress(rs.getString("address"));
            memberDto.setJoindate(rs.getString("joindate"));
            memberDto.setGrade(rs.getString("grade"));
            memberDto.setCity(rs.getString("city"));
        }
        rs.close(); ps.close();
    } catch (Exception e){
        System.out.println(e);
    }
    return memberDto;
}
```

### 2단계: Service에 특정 회원 조회 메소드 추가 (`MemberService.java`)

DAO에 만든 메소드를 호출하는 Service 메소드를 만듭니다.

```java
// MemberService.java 파일에 아래 내용을 추가하세요.

// 5. 개별 회원 조회
public MemberDto getMember(int custno){
    return memberDao.getMember(custno);
}
```

### 3단계: Controller에 특정 회원 조회 메소드 추가 (`MemberController.java`)

자바스크립트의 `fetch` 요청을 직접적으로 받을 컨트롤러 메소드를 만듭니다. 이 메소드가 바로 "데이터를 주세요" 요청을 처리할 새로운 창구입니다.

```java
// MemberController.java 파일에 아래 내용을 추가하세요.

// 5. 개별 회원 조회
@GetMapping("/member") // GET 방식, /assessment/member 주소로 요청 받음
public MemberDto getMember(@RequestParam int custno){
    return memberService.getMember(custno);
}
```

### 4단계: JavaScript 수정 (`update.js`)

이제 자바스크립트가 새로운 창구로 데이터를 요청하고, 받은 데이터를 `input`에 채워 넣도록 코드를 수정합니다. `update.js`의 `memberUpdateView` 함수를 아래 내용으로 전체 교체하세요.

```javascript
// [1] 기존 회원 정보 불러오기
const memberUpdateView = async () => {
    console.log('memberUpdateView XXOK');
    
    // URL에서 수정할 회원의 번호(custno) 가져오기
    const custno = new URLSearchParams(location.search).get('custno');

    // 만약 custno가 없으면 실행 중단
    if (!custno) {
        alert('회원 번호가 올바르지 않습니다.');
        return;
    }

    // 3단계에서 만든 새로운 Controller 메소드에 데이터 요청
    const response = await fetch(`/assessment/member?custno=${custno}`);
    const data = await response.json();

    // 응답받은 데이터(data)가 없으면 실행 중단
    if (!data) {
        alert('존재하지 않는 회원이거나 정보를 불러오는데 실패했습니다.');
        return;
    }

    // 각 input 태그들을 class 이름으로 선택
    const custnoInput = document.querySelector('.custno');
    const custnameInput = document.querySelector('.custname');
    const phoneInput = document.querySelector('.phone');
    const addressInput = document.querySelector('.address');
    const joindateInput = document.querySelector('.joindate'); // joindate로 변경
    const gradeInput = document.querySelector('.grade');
    const cityInput = document.querySelector('.city');

    // 가져온 데이터(data)를 각 input의 value 값으로 설정
    custnoInput.value = data.custno;
    custnameInput.value = data.custname;
    phoneInput.value = data.phone;
    addressInput.value = data.address;
    joindateInput.value = data.joindate.split(' ')[0]; // 날짜만 표시 (YYYY-MM-DD)
    gradeInput.value = data.grade;
    cityInput.value = data.city;
}
memberUpdateView(); // 페이지가 열릴 때 함수 실행
```

### 5단계: JSP 수정 (`update.jsp`)

`update.js`에서 `joindate` 클래스를 사용하도록 변경했으니, `update.jsp`의 가입일자 `input` 클래스 이름도 맞춰주는 것이 좋습니다.

```html
<!-- update.jsp -->
...
<!-- 회원번호 input의 클래스에서 nextNo 제거 -->
회원번호 <input class="custno" type="number" readonly/> 
...
<!-- 가입일자 input의 클래스를 date에서 joindate로 변경 -->
가입일자 <input class="joindate" type="text" readonly/>
...
```

### 6단계: (보너스) 수정 기능 완성하기

기존 정보를 불러왔으니, 이제 "수정" 버튼을 눌렀을 때 실제로 정보가 바뀌도록 `update.js`의 `memberUpdate` 함수도 수정해야 합니다.

```javascript
// update.js의 memberUpdate 함수를 아래 코드로 교체하세요.

// [2] 수정 처리
const memberUpdate = async () => {
    console.log('memberUpdate XXOK');

    // 수정할 정보들을 각 input에서 가져오기
    const custno = document.querySelector('.custno').value; // 수정할 대상이 누군지 알려주기 위해 custno도 포함
    const custname = document.querySelector('.custname').value;
    const phone = document.querySelector('.phone').value;
    const address = document.querySelector('.address').value;
    const grade = document.querySelector('.grade').value;
    const city = document.querySelector('.city').value;

    // 객체로 만들기
    const obj = {
        custno: custno,
        custname: custname,
        phone: phone,
        address: address,
        grade: grade,
        city: city
    };

    // 유효성 검사 (생략) ...

    // fetch로 서버에 수정 요청 (PUT 방식)
    const option = {
        method: "PUT", // PUT으로 변경
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(obj)
    }
    
    const response = await fetch("/assessment/update", option);
    const result = await response.json();
    
    if (result == true) {
        alert('회원정보수정이 완료 되었습니다!');
        location.href = "/assessment/list.jsp"; // 수정 완료 후 목록 페이지로 이동
    } else {
        alert('회원정보수정이 실패했습니다.');
    }
}
```

---

이제 모든 과정이 끝났습니다. 위 순서대로 진행하시면 수정 페이지에 기존 회원 정보가 정상적으로 출력되고, 수정 기능까지 완벽하게 동작할 것입니다.
