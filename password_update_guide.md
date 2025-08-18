
# 비밀번호 변경 실패 현상 분석 및 개선 가이드

## 1. 문제 원인 분석

현재 비밀번호 변경 프로세스는 다음과 같이 이루어집니다.

1.  **프론트엔드 (`pwdupdate.jsp` & `pwdupdate.js`):** 사용자가 기존 비밀번호와 새 비밀번호를 입력하고 버튼을 클릭하면 `onPwdUpdate()` 함수가 실행됩니다.
2.  **백엔드 (Controller -> `MemberDao`):** `onPwdUpdate()` 함수는 서버에 비밀번호 변경을 요청하고, 컨트롤러는 이 요청을 받아 `MemberDao.updatePassword()` 메소드를 호출합니다.

문제의 핵심은 **프론트엔드에서 백엔드로 데이터를 전달하는 과정**에 있을 가능성이 매우 높습니다. `MemberDao.java`의 `updatePassword` 메소드는 다음과 같이 정의되어 있습니다.

```java
public boolean updatePassword(int mno, Map<String, String> map) {
    try {
        String sql = "update member set mpwd = ? where mno = ? and mpwd = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, map.get("newpwd")); // (1) 새 패스워드
        ps.setInt(2, mno);
        ps.setString(3, map.get("oldpwd")); // (2) 기존 패스워드
        int count = ps.executeUpdate();
        return count == 1;
    } catch (Exception e) {
        System.out.println(e);
    }
    return false;
}
```

이 메소드는 `Map`에서 `"newpwd"`와 `"oldpwd"`라는 정확한 키(key)로 값을 찾아 사용합니다. 만약 `pwdupdate.js`에서 AJAX 요청 시 보내는 데이터의 키 이름이 이와 다르거나, 데이터 형식이 잘못되었다면 `map.get()`은 `null`을 반환하게 됩니다. 결국 `where` 절의 `mpwd = ?` 부분이 `mpwd = null`이 되어 업데이트 대상(count)을 찾지 못하므로, 메소드는 항상 `false`를 반환하게 됩니다.

"비밀번호 확인은 통과했다"는 사실로 미루어 보아, 사용자가 입력한 기존 비밀번호 값 자체는 정확합니다. 따라서 문제는 **데이터의 키(key) 또는 전송 방식의 불일치**입니다.

## 2. 개선 방안

이 문제를 해결하기 위해 `pwdupdate.js`의 AJAX 통신 부분을 수정하여, 백엔드가 기대하는 데이터 형식과 키에 정확히 맞춰주어야 합니다.

### `pwdupdate.js` 수정 제안

`pwdupdate.js` 파일의 `onPwdUpdate` 함수를 다음과 같이 수정하는 것을 권장합니다.

```javascript
// pwdupdate.js

function onPwdUpdate() {
    // 1. 입력받은 값 가져오기
    const oldpwd = document.querySelector('.oldpwd').value;
    const newpwd = document.querySelector('.newpwd').value;

    // 2. 서버에 전송할 데이터 객체 생성 (DAO의 Map 키와 일치시켜야 함)
    const data = {
        oldpwd: oldpwd,
        newpwd: newpwd
    };

    // 3. AJAX (fetch API)를 이용한 비동기 통신
    fetch('/member/password', { // 컨트롤러에 설정된 URL 경로
        method: 'PUT', // RESTful API 설계에 따라 리소스 전체 수정은 PUT 사용 권장
        headers: { 'Content-Type': 'application/json' }, // 전송하는 데이터의 타입 명시
        body: JSON.stringify(data) // JavaScript 객체를 JSON 문자열로 변환하여 전송
    })
    .then(response => response.json()) // 서버 응답을 JSON 형태로 파싱
    .then(result => {
        // 4. 서버로부터 받은 결과에 따른 처리
        if (result) {
            alert('비밀번호가 성공적으로 변경되었습니다. 다시 로그인해주세요.');
            location.href = '/member/logout'; // 로그아웃 처리 후 로그인 페이지로 이동
        } else {
            alert('기존 비밀번호가 일치하지 않거나, 변경에 실패했습니다.');
        }
    })
    .catch(error => {
        console.error('비밀번호 변경 중 오류 발생:', error);
        alert('오류가 발생했습니다. 잠시 후 다시 시도해주세요.');
    });
}
```

### Spring Controller 수정 제안

위 JavaScript 코드와 원활하게 통신하기 위해, Spring Controller의 해당 메소드도 다음과 같이 `@RequestBody` 어노테이션을 사용하여 JSON 데이터를 `Map`으로 받을 수 있도록 구성해야 합니다.

```java
// MemberController.java (예시)

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService; // 또는 MemberDao

    @PutMapping("/password")
    public boolean updatePassword(@RequestBody Map<String, String> map, HttpSession session) {
        // 1. 세션에서 현재 로그인된 회원 번호(mno) 가져오기
        Object sessionObj = session.getAttribute("loginMno");
        if (sessionObj == null) {
            return false; // 로그인되어 있지 않으면 실패
        }
        int mno = (int) sessionObj;

        // 2. 서비스(또는 DAO) 계층으로 mno와 데이터 전달
        return memberService.updatePassword(mno, map);
    }
}
```

## 3. 핵심 요약

-   **문제:** `pwdupdate.js`에서 보내는 데이터의 **키(key) 이름**이나 **형식**이 `MemberDao.updatePassword`가 기대하는 `Map`의 키(`"oldpwd"`, `"newpwd"`)와 일치하지 않아 발생합니다.
-   **해결:**
    1.  `pwdupdate.js`에서 `fetch`를 사용하여 AJAX 요청을 보낼 때, `body`에 **정확한 키를 가진 JavaScript 객체**를 `JSON.stringify()`로 변환하여 담아 보냅니다.
    2.  `headers`에 `'Content-Type': 'application/json'`을 명시하여 서버가 JSON 데이터를 올바르게 인식하도록 합니다.
    3.  Spring Controller에서는 `@RequestBody` 어노테이션을 사용하여 해당 JSON 데이터를 `Map`으로 받도록 합니다.

위 가이드에 따라 `pwdupdate.js`와 컨트롤러 코드를 점검하고 수정하면 문제가 해결될 것입니다.
