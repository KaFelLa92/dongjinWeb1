# 연락처 유효성 검사 컴포넌트 사용 가이드

이 문서는 여러 페이지(`회원가입`, `정보수정`)에서 연락처 유효성 검사 로직을 재사용하는 방법을 안내합니다.

공통 로직을 `member-validation.js` 파일로 분리하여 중복 코드를 제거하고 유지보수성을 높입니다.

## 1. 공통 유효성 검사 스크립트 생성

먼저, 연락처 유효성 검사 로직을 담은 `member-validation.js` 파일을 아래와 같이 생성합니다. 이 파일은 이미 생성해드렸습니다.

**위치:** `src/main/resources/static/js/member/member-validation.js`

```javascript
/**
 * 연락처 중복 및 유효성 검사를 수행하는 재사용 가능한 함수
 * @param {string} phoneNumber - 검사할 연락처 문자열
 * @returns {Promise<{isValid: boolean, message: string}>} - 유효성 검사 결과 객체를 담은 프로미스
 */
async function validatePhoneNumber(phoneNumber) {
    // 1. 유효성 검사 (길이 및 하이픈 포함 여부)
    if (phoneNumber.length !== 13 || !phoneNumber.includes('-')) {
        return { isValid: false, message: '-(하이픈) 포함한 13글자 연락처 입력해주세요.' };
    }

    // 2. 유효성 검사 (서버 중복 확인)
    try {
        const response = await fetch(`/member/check?type=mphone&data=${phoneNumber}`);
        if (!response.ok) {
            // 서버 에러 처리
            return { isValid: false, message: '서버 통신 오류. 잠시 후 다시 시도해주세요.' };
        }
        const isDuplicate = await response.json();

        if (isDuplicate) {
            return { isValid: false, message: '현재 사용중인 연락처입니다.' };
        } else {
            return { isValid: true, message: '사용 가능한 연락처입니다.' };
        }
    } catch (error) {
        console.error('Phone validation error:', error);
        return { isValid: false, message: '연락처 검사 중 오류가 발생했습니다.' };
    }
}
```

---

## 2. 회원가입 페이지 (`signup.jsp`, `signup.js`) 수정

### 2.1. `signup.jsp` 파일 수정

`<body>` 태그 하단에 새로 만든 `member-validation.js` 스크립트를 추가합니다.

```jsp
...
        </div>

        <!-- 스크립트 영역 -->
        <script src="/js/member/member-validation.js"></script> <%-- 이 부분 추가 --%>
        <script src="/js/member/signup.js"></script>

    </body>
</html>
```

### 2.2. `signup.js` 파일 수정

기존 `phonecheck` 함수를 새로운 `validatePhoneNumber` 함수를 사용하도록 아래와 같이 수정합니다.

```javascript
// [3] 연락처 중복검사
const phonecheck = async () => {
    const mphoneInput = document.querySelector('.phoneInput');
    const phonecheckResult = document.querySelector('.phonecheck');

    const result = await validatePhoneNumber(mphoneInput.value);

    phonecheckResult.innerHTML = result.message;
    signPass[1] = result.isValid; // 기존 유효성 검사 결과 배열 업데이트
}
```

---

## 3. 정보 수정 기능 (`update.js`)에 적용

`update.js`가 사용되는 정보 수정 페이지(예: `update.jsp`)에서도 동일한 컴포넌트를 사용할 수 있습니다.

### 3.1. 정보 수정 JSP 파일 (예: `update.jsp`) 수정

`signup.jsp`와 마찬가지로 `<body>` 태그 하단에 `member-validation.js` 스크립트를 추가해야 합니다.

```jsp
<%-- 예시: /member/update.jsp --%>
...
        </div>

        <!-- 스크립트 영역 -->
        <script src="/js/member/member-validation.js"></script> <%-- 이 부분 추가 --%>
        <script src="/js/member/update.js"></script>

    </body>
</html>
```

### 3.2. `update.js` 파일 수정

`update.js` 파일에 연락처 유효성 검사 로직을 추가합니다. `onkeyup` 이벤트를 HTML에 직접 추가하거나, JavaScript에서 이벤트 리스너를 등록하여 사용할 수 있습니다.

```javascript
// update.js

// ... 기존 onUpdateFind, onUpdate 함수 ...

// [3] 연락처 유효성 검사
// HTML input 태그에 onkeyup="handlePhoneCheck()" 추가 필요
// 예: <input type="text" class="mphone" onkeyup="handlePhoneCheck()">
let isPhoneValid = false; // 정보 수정 시 연락처 유효성 상태 저장 변수

const handlePhoneCheck = async () => {
    const phoneInput = document.querySelector('.mphone');
    // 결과를 표시할 DOM 요소가 필요합니다. (예: <div class="phone-check-result"></div>)
    const resultElement = document.querySelector('.phone-check-result');

    const result = await validatePhoneNumber(phoneInput.value);

    if (resultElement) {
        resultElement.innerHTML = result.message;
    }
    isPhoneValid = result.isValid;
};

// [2] 정보 수정 함수 수정
const onUpdate = async () => {
    // 연락처 유효성 검사 통과 여부 확인
    if (!isPhoneValid) {
        alert('연락처를 확인해주세요.');
        return;
    }

    console.log('onUpdate XXOK');

    // ... 기존 로직 ...
    const mname = document.querySelector('.mname').value;
    const mphone = document.querySelector('.mphone').value;

    // ... fetch ...
}
```

### 3.3. 정보 수정 페이지 HTML 수정 제안

`update.js`의 `handlePhoneCheck` 함수가 동작하려면, 정보 수정 페이지의 HTML(`update.jsp`로 추정)은 아래와 같이 구성되어야 합니다.

```html
<!-- 이름 -->
이름: <input type="text" class="mname"> <br/>

<!-- 연락처 -->
연락처: <input type="text" class="mphone" onkeyup="handlePhoneCheck()"> <br/>
<div class="phone-check-result"></div> <%-- 검사 결과 메시지 표시 영역 --%>

<!-- 수정 버튼 -->
<button type="button" onclick="onUpdate()">정보 수정</button>
```

이제 위 가이드를 따라 프로젝트의 코드를 수정하면, 여러 곳에서 동일한 연락처 유효성 검사 로직을 간편하게 재사용할 수 있습니다.
