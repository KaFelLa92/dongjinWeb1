# 회원 관리 프론트엔드 문제 해결 가이드

안녕하세요. 현재 발생한 두 가지 문제인 **1) 회원 등록 후의 잘못된 페이지 이동**과 **2) 회원 정보 수정 기능 미작동**에 대한 원인 분석 및 해결 방법을 안내해 드립니다.

## 문제 1: 회원 정보 수정이 실행되지 않는 현상

### 원인 분석

가장 핵심적인 원인은, "수정" 버튼을 눌러 서버에 데이터를 보낼 때 **어떤 회원을 수정해야 하는지에 대한 식별 정보(`custno`)가 누락**되었기 때문입니다.

`update.js`의 `memberUpdate` 함수 내에서 서버로 전송할 `obj` 객체를 생성할 때, `custno`가 주석 처리되어 있어 실제 요청에는 포함되지 않았습니다. 따라서 서버는 누구의 정보를 수정해야 할지 알 수 없어 업데이트를 수행하지 못합니다.

### 해결 방법

`update.js` 파일을 열어, `memberUpdate` 함수에서 `obj` 객체에 `custno`를 포함하도록 수정합니다.

#### **`update.js` 수정 코드**

아래와 같이 `const obj`를 만드는 부분에서 `custno`의 주석을 해제하고, `PUT` 요청 시 `custno`도 함께 전송되도록 수정합니다.

```javascript
// update.js

// [2] 수정
const memberUpdate = async () => {
    console.log('memberUpdate XXOK'); // 로그 메시지를 명확하게 변경하는 것을 추천합니다.
    // 입력할 값들
    const custno = document.querySelector('.custno').value;
    const custname = document.querySelector('.custname').value;
    const phone = document.querySelector('.phone').value;
    const address = document.querySelector('.address').value;
    const grade = document.querySelector('.grade').value;
    const city = document.querySelector('.city').value;

    // 객체화
    const obj = {
        custno, // [수정] 주석을 해제하여 custno를 요청 데이터에 포함시킵니다.
        custname,
        phone,
        address,
        grade,
        city
    }

    // 유효성 검사 (필요시 추가)
    if (!custname) { alert('회원성명이 입력되지 않았습니다.'); return; }
    if (!phone) { alert('회원전화가 입력되지 않았습니다.'); return; }
    if (!address) { alert('회원주소가 입력되지 않았습니다.'); return; }
    if (!grade) { alert('고객등급이 입력되지 않았습니다.'); return; }
    if (!city) { alert('도시코드가 입력되지 않았습니다.'); return; }


    // fetch
    const option = {
        method: "PUT", // 수정 요청이므로 PUT 메소드를 사용합니다.
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(obj)
    }
    // fecth exe
    // [수정] PUT 요청 시에는 수정할 대상의 custno를 URL에 포함하지 않고, body에 담아 보냅니다.
    const response = await fetch("/assessment/update", option) 
    const data = await response.json();
    // result
    if (data == true) {
        alert('회원정보수정이 완료 되었습니다!')
        location.href = "/assessment/list.jsp";
    } else {
        alert('회원정보수정이 실패했습니다. 관리자에게 문의하세요.')
    }

} // func end
```

---

## 문제 2: 회원 등록 후 `list.jsp`가 아닌 `list`로 이동하는 현상

### 원인 분석

`add.js` 파일에 작성된 `location.href = "/assessment/list.jsp";` 코드는 문법적으로 올바릅니다. 이 현상은 코드 자체의 문제라기보다는, `form` 태그의 기본 동작이 JavaScript의 페이지 이동 코드와 충돌을 일으켰을 가능성이 있습니다.

"등록" 버튼이 `form` 내부에 있을 때, 브라우저가 이를 폼 제출(submit)로 오인하여 의도치 않은 경로로 페이지를 이동시키려 할 수 있습니다.

### 해결 방법

`type="button"`으로 이미 폼 제출을 방지하고 있지만, 코드의 안정성을 높이기 위해 JavaScript에서 명시적으로 폼의 기본 동작을 막는 코드를 추가합니다. 이렇게 하면 오직 `location.href` 코드만이 페이지 이동을 제어하게 됩니다.

#### **1단계: `add.jsp` 수정**

`onclick` 이벤트 핸들러에 `event` 객체를 넘겨주도록 코드를 수정합니다.

```html
<!-- add.jsp의 <p> 태그 부분을 아래와 같이 수정 -->

<p style="text-align:center;">
    <!-- onclick 핸들러에 'event'를 전달합니다. -->
    <button type="button" onclick="memberAdd(event)"> 등록 </button>
    <button type="button" onclick="memberToList()"> 조회 </button>
</p>
```

#### **2단계: `add.js` 수정**

`memberAdd` 함수가 `event` 매개변수를 받도록 하고, 함수의 가장 첫 줄에 `event.preventDefault();`를 추가합니다.

```javascript
// add.js

const memberAdd = async (event) => { // event 매개변수 추가
    event.preventDefault(); // [추가] 폼(form)의 기본 전송(submit) 동작을 명시적으로 막습니다.

    console.log('memberAdd XXOK');
    // 입력할 값들
    const custname = document.querySelector('.custname').value;
    const phone = document.querySelector('.phone').value;
    const address = document.querySelector('.address').value;
    const grade = document.querySelector('.grade').value;
    const city = document.querySelector('.city').value;

    // (이하 코드는 기존과 동일)
    const obj = {
        custname,
        phone,
        address,
        grade,
        city
    }

    // 유효성 검사
    if (!custname) {
        alert('회원성명이 입력되지 않았습니다.')
        return;
    } else if (!phone) {
        alert('회원전화가 입력되지 않았습니다.')
        return;
    } else if (!address) {
        alert('회원주소가 입력되지 않았습니다.')
        return;
    } else if (!grade) {
        alert('고객등급이 입력되지 않았습니다.')
        return;
    } else if (!city) {
        alert('도시코드가 입력되지 않았습니다.')
        return;
    }

    // fetch
    const option = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(obj)
    }
    // fecth exe
    const response = await fetch("/assessment/add", option)
    const data = await response.json();
    // result
    if (data == true) {
        alert('회원등록이 완료 되었습니다!')
        location.href = "/assessment/list.jsp";
    } else {
        alert('회원등록이 실패했습니다. 관리자 문의 010-XXXX-XXXX')
    }

} // func end
```

---

위 가이드에 따라 코드를 수정하시면 두 가지 문제 모두 해결될 것입니다.