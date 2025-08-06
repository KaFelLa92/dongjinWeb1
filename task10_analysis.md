# 종합과제10 (task10) waitDelete 기능 JS 오류 분석 보고서

## 1. 문제 현상
`task10.html` 페이지에서 '대기삭제실행' 버튼을 클릭하면 `task10.js`의 `waitDelete()` 함수가 호출됩니다. 이 함수는 서버에 `DELETE` 요청을 보내 특정 대기 정보를 삭제해야 하지만, 실제로는 삭제가 이루어지지 않고, 브라우저 개발자 도구(F12)의 콘솔에 404 (Not Found) 오류가 발생합니다.

## 2. 분석 과정

### 2-1. Frontend 분석 (task10.js)
먼저 JavaScript의 `waitDelete` 함수 코드를 확인했습니다.

```javascript
// C:\Users\tj-bu-702-04\Desktop\cording\dongjinWeb1\src\main\resources\static\종합\task10.js

const waitDelete = () => {
    console.log("waitDelete xxok");
    let wno = 4; // 삭제할 번호 (샘플)
    let option = { method : "DELETE"};
    
    // fetch를 이용해 쿼리 스트링 방식으로 DELETE 요청 전송
    fetch(`/waiting?wno=${wno}` , option)
        .then(response => response.json())
        .then(data => {console.log(data);})
        .catch( error => {console.log(error)}) // <-- 여기서 404 에러 발생
}
```
- **분석 결과**: JavaScript 코드는 `/waiting` 경로로 `wno`를 쿼리 스트링에 담아 `DELETE` 요청을 정상적으로 보내고 있습니다. **Frontend 코드에는 문제가 없습니다.**

### 2-2. Backend 분석 (WaitingController.java)
다음으로 요청을 받는 스프링 컨트롤러의 코드를 확인했습니다.

```java
// C:\Users\tj-bu-702-04\Desktop\cording\dongjinWeb1\src\main\java\종합\과제10\controller\WaitingController.java

@RestController
public class WaitingController {
    // ... 다른 메소드 생략 ...

    @DeleteMapping ("/waiting")
    public boolean waitDelete(@RequestParam int wno){
        boolean result = waitingDao.waitDelete( wno );
        return result;
    }
}
```
- **분석 결과**: `@DeleteMapping("/waiting")` 어노테이션을 통해 `/waiting` 경로의 DELETE 요청을 처리하도록 정확히 매핑되어 있습니다. `@RequestParam`으로 `wno`를 받는 것도 정상적입니다. **Backend 로직 자체에도 문제가 없습니다.**

## 3. 핵심 원인: 잘못된 `AppStart` 실행으로 인한 컴포넌트 스캔 실패

그렇다면 코드 로직에 문제가 없는데 왜 404 오류가 발생할까요? **원인은 현재 프로젝트 구조와 스프링 부트의 동작 방식에 있습니다.**

1.  **스프링 부트의 컴포넌트 스캔(Component Scan)**
    - `@SpringBootApplication` 어노테이션이 붙은 `AppStart` 클래스를 실행하면, 스프링은 해당 클래스가 위치한 패키지와 그 하위 패키지 전체를 스캔합니다.
    - 이 과정에서 `@RestController`, `@Service`, `@Repository` 등이 붙은 클래스들을 찾아 메모리에 등록(Bean 등록)하고, `@DeleteMapping` 같은 요청 매핑을 활성화합니다.

2.  **현재 프로젝트의 구조적 문제**
    - 현재 프로젝트(`dongjinWeb1`)에는 여러 개의 `AppStart.java` 파일이 각기 다른 패키지에 존재합니다. (예: `example.day01`, `example.day02`, `종합.과제10` 등)
    - `waitDelete` 기능을 담당하는 `WaitingController`는 `종합.과제10.controller` 패키지에 있습니다.
    - 따라서 `WaitingController`가 정상적으로 동작하려면 **반드시 `종합.과제10` 패키지에 있는 `AppStart.java`를 실행해야만 합니다.**

3.  **결론**
    - 만약 `종합.과제10.AppStart`가 아닌 다른 패키지의 `AppStart` (예: `example.day01.AppStart`)를 실행하면, 스프링의 컴포넌트 스캔 범위에 `WaitingController`가 포함되지 않습니다.
    - 결과적으로 `WaitingController`는 메모리에 등록되지 않고, `@DeleteMapping("/waiting")` 매핑도 존재하지 않게 됩니다.
    - 이 상태에서 JavaScript가 `/waiting`으로 요청을 보내면, 서버는 해당 주소를 처리할 수 있는 컨트롤러를 찾지 못해 **404 (Not Found) 오류**를 반환하는 것입니다.

## 4. 해결 방안

IntelliJ에서 실행할 `main` 클래스를 **`종합.과제10.AppStart`** 로 정확히 지정하고 서버를 재시작하십시오.

1.  IntelliJ 우측 상단의 실행 설정 드롭다운 메뉴를 클릭합니다.
2.  `Edit Configurations...`를 선택합니다.
3.  `Application` 설정에서 `Main class` 항목의 값을 `종합.과제10.AppStart`로 변경하거나, 해당 클래스를 찾아 선택합니다.
4.  설정을 저장하고 서버를 다시 실행한 뒤, `task10.html`에서 삭제 기능을 테스트합니다.

## 5. 추가 권장 사항

- **하나의 프로젝트에는 하나의 `AppStart`만 유지:** 현재와 같이 여러 `AppStart`가 있으면 어떤 서버가 실행되는지 혼동하기 쉽습니다. 실무에서는 하나의 프로젝트에 단 하나의 진입점(`main` 메소드)만 두는 것이 일반적입니다. 각 실습은 별도의 프로젝트로 분리하는 것을 권장합니다.
- **Spring DI 사용:** 현재 `WaitingDao`를 `WaitingDao.getInstance()`와 같은 싱글톤 패턴으로 직접 호출하고 있습니다. 스프링에서는 DAO 클래스에 `@Repository`를, 컨트롤러에는 `@Autowired`를 사용하여 의존성을 주입(DI)받는 방식이 표준입니다. 이를 통해 코드의 결합도를 낮추고 테스트를 용이하게 할 수 있습니다.
