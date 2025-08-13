# add.js 개선 방안

## 문제점

1.  **회원번호 자동 생성 실패**: `add.js`의 `memberAddView` 함수가 `/assessment/add`로 GET 요청을 보내고 있습니다. 하지만 해당 주소는 `POST` 요청을 통해 회원 정보를 받아 등록하는 기능만 구현되어 있을 가능성이 높습니다. 따라서 `nextNo` 값을 받아오지 못하고 있습니다.
2.  **가입일자 미출력**: `new Date()`를 사용하여 현재 날짜를 가져오는 로직이 있으나, 이를 `YYYY-MM-DD` 형식으로 변환하여 `input` 요소에 값을 설정하는 코드가 완전하지 않습니다.

## 해결 방안

### 1. 회원번호(custno) 자동 생성을 위한 Spring Controller 및 JavaScript 수정

**Spring Controller 수정** (`MemberController.java`)

GET 요청 시 다음 회원번호를 JSON 형태로 반환하는 메소드를 추가해야 합니다.

```java
@GetMapping("/assessment/nextNo")
@ResponseBody
public Map<String, Object> getNextNo() {
    Map<String, Object> map = new HashMap<>();
    int nextNo = memberService.getNextNo(); // Service에서 nextNo를 가져오는 로직
    map.put("nextNo", nextNo);
    return map;
}
```

**JavaScript 수정** (`add.js`)

`memberAddView` 함수에서 `nextNo`를 가져오는 `fetch` 요청 주소를 수정해야 합니다.

```javascript
// [2] 등록에 정해진 값 투사하기
const memberAddView = async () => {
    console.log('memberAddView XXOK');
    // fetch
    const response = await fetch("/assessment/nextNo"); // 수정된 부분
    const data = await response.json();
    // 이미 value 있는 값들
    // DAO에서 현재 회원번호 +1하는 코드 만들어서 넣기
    const nextNo = document.querySelector('.nextNo');
    nextNo.value = data.nextNo;

    // joindate는 new date() 로 오늘 날짜 넣으면 됨
    const joindate = document.querySelector('.joindate');
    const today = new Date();
    const year = today.getFullYear();
    const month = ('0' + (today.getMonth() + 1)).slice(-2);
    const day = ('0' + today.getDate()).slice(-2);
    joindate.value = `${year}-${month}-${day}`;
}
memberAddView();
```

### 2. 현재 날짜를 YYYY-MM-DD 형식으로 변환하는 JavaScript 함수

`new Date()` 객체는 현재 날짜 및 시간 정보를 가지고 있지만, 이를 그대로 `input`의 `value`에 넣으면 원하는 `YYYY-MM-DD` 형식이 아닌 다른 형태로 출력될 수 있습니다. 따라서 아래와 같이 포맷을 맞춰주는 과정이 필요합니다.

```javascript
const today = new Date();
const year = today.getFullYear();
const month = ('0' + (today.getMonth() + 1)).slice(-2); // 월은 0부터 시작하므로 +1, 두 자리로 맞추기
const day = ('0' + today.getDate()).slice(-2); // 두 자리로 맞추기

const formattedDate = `${year}-${month}-${day}`;
console.log(formattedDate); // 예: 2025-08-13
```

이 코드를 `memberAddView` 함수 내에 적용하여 `joindate` input 요소의 값으로 설정하면 됩니다.