
# Day03 수업 정리 및 실습 가이드

안녕하세요! Day03 수업을 놓치셨군요. 괜찮습니다. 이 가이드만 따라오시면 수업 내용을 완벽하게 이해하고 실습까지 해결할 수 있습니다. 천천히 따라와 주세요.

---

## 1부: Day03 핵심 개념 정리

Day03의 핵심 주제는 **"스프링 컨트롤러에서 클라이언트의 요청(Request) 데이터를 받는 방법"** 입니다. 클라이언트(웹 브라우저, 앱 등)가 서버에 데이터를 보낼 때, 스프링이 이 데이터를 어떻게 받아서 처리하는지 배우는 과정입니다.

### 1. `@RestController` 다시 보기

- Day02에서 배운 `@RestController`는 `@Controller`와 `@ResponseBody`가 합쳐진 어노테이션입니다.
- 이 어노테이션을 사용하면 메소드의 반환 값을 자동으로 HTTP 응답 본문(Body)으로 만들어주기 때문에, RESTful API를 만들 때 매우 편리합니다. Day03의 모든 실습은 이 어노테이션을 기반으로 합니다.

### 2. 클라이언트의 요청 데이터를 받는 3가지 방법

클라이언트가 서버로 데이터를 보내는 방식은 크게 2가지가 있습니다.
- **쿼리 스트링 (Query String)**: URL 주소 뒤에 `?`를 붙여 `key=value` 형태로 데이터를 보내는 방식입니다. (예: `http://localhost:8080/day03/param1?name=유재석`)
- **요청 본문 (Request Body)**: HTTP 요청의 몸통(Body)에 데이터를 담아 보내는 방식입니다. 주로 JSON 형태로 보냅니다.

스프링은 이 두 가지 방식의 데이터를 받기 위해 다양한 어노테이션을 제공합니다.

#### ① `@RequestParam`: 단일 파라미터 받기

URL의 쿼리 스트링 값을 직접 메소드 매개변수로 받을 때 사용합니다.

- **사용법**: `public void method(@RequestParam String name, @RequestParam int age) { ... }`
- **주요 속성**:
  - `name`: URL의 매개변수 이름과 자바 매개변수 이름이 다를 때 매핑해줍니다. (예: `@RequestParam(name="username") String name`) 이름이 같다면 생략 가능합니다.
  - `defaultValue`: 요청 값(쿼리 스트링)이 없을 경우 사용할 기본값을 지정합니다. (예: `@RequestParam(defaultValue = "홍길동") String name`)
  - `required`: 해당 파라미터의 필수 여부를 결정합니다. `true`가 기본값이며, 파라미터가 없으면 400 에러가 발생합니다. (예: `@RequestParam(required = false) String name`)

**예시 코드 (RestController1.java 참고)**
```java
// GET http://localhost:8080/day03/param1?name=유재석
@GetMapping("/day03/param1")
public boolean method2(@RequestParam String name) {
    System.out.println("name = " + name); // "유재석" 출력
    return true;
}

// GET http://localhost:8080/day03/param2?name=강호동&age=20
@GetMapping("/day03/param2")
public int method3(@RequestParam String name, @RequestParam int age) {
    System.out.println("name = " + name + ", age = " + age); // "강호동", 20 출력
    return 3;
}
```

#### ② `@ModelAttribute`: 객체(DTO)로 파라미터 받기

쿼리 스트링으로 들어온 여러 파라미터를 자동으로 객체(DTO)의 필드에 매핑(바인딩)해줍니다.

- **조건**: DTO에 반드시 **기본 생성자**와 **setter 메소드**가 있어야 합니다.
- **특징**: `@ModelAttribute` 어노테이션은 생략 가능합니다. 스프링이 알아서 DTO 타입이면 이 방식으로 처리합니다.

**예시 코드 (RestController1.java 참고)**
```java
// GET http://localhost:8080/day03/param4?name=유재석&age=40
@DeleteMapping("/day03/param4")
public int method5(TaskDto taskDto) { // @ModelAttribute 생략됨
    // new TaskDto() 후, taskDto.setName("유재석"), taskDto.setAge(40) 자동 실행
    System.out.println("taskDto = " + taskDto);
    return 3;
}
```

#### ③ `@RequestBody`: JSON 데이터 받기

HTTP 요청 본문(Body)에 담겨 온 JSON 데이터를 객체(DTO)나 Map으로 매핑(바인딩)할 때 사용합니다.

- **조건**: DTO에 **기본 생성자**와 **setter**가 필요합니다.
- **특징**: 주로 `POST`, `PUT` 요청에서 사용됩니다. 클라이언트에서 데이터를 보낼 때 `Content-Type` 헤더를 `application/json`으로 설정해야 합니다.

**예시 코드 (RestController1.java 참고)**
```java
// POST http://localhost:8080/day03/param5
// Body: { "name" : "유재석" , "age" : 40 }
@PostMapping("/day03/param5")
public boolean method6(@RequestBody TaskDto taskDto) {
    System.out.println("taskDto = " + taskDto);
    return true;
}

// PUT http://localhost:8080/day03/param6
// Body: { "key1" : "value1", "key2" : "value2" }
@PutMapping("/day03/param6")
public int method7(@RequestBody Map<String,String> map) {
    System.out.println("map = " + map);
    return 3;
}
```

### 3. 어노테이션 비교 (매우 중요!)

| 구분 | `@RequestParam` | `@ModelAttribute` | `@RequestBody` |
| :--- | :--- | :--- | :--- |
| **역할** | 단일 파라미터 바인딩 | 복수 파라미터 객체 바인딩 | **요청 본문(Body)** 객체 바인딩 |
| **데이터 소스** | 쿼리 스트링, 폼 데이터 | 쿼리 스트링, 폼 데이터 | **JSON**, XML 등 |
| **주요 타입** | `String`, `int` 등 기본형, `Map` | DTO (사용자 정의 객체) | DTO, `Map` |
| **HTTP 메소드** | `GET`, `DELETE` 등 | `GET`, `POST` 등 | `POST`, `PUT` |
| **생략 가능** | `name`이 같으면 가능 | **기본 적용 (생략 가능)** | **생략 불가능** |

---

## 2부: Day03 실습 가이드

Day02에서 만들었던 게시판 API에 오늘 배운 "데이터 전달" 기능을 추가하여 완성도 있는 API로 만들어 보겠습니다.

**실습 목표**: `BoardController`의 각 API가 명세에 맞게 데이터를 주고받도록 수정하기

### Step 1: `BoardDto` 확인하기

`@ModelAttribute`와 `@RequestBody`를 사용하려면 DTO에 기본 생성자와 `getter/setter`가 필수입니다. Day02에 만든 `BoardDto`를 확인해 봅시다.

`example/day02/_3실습/BoardDto.java`
```java
public class BoardDto {
    private int bno;
    private String btitle;

    // 1. 기본 생성자 (O)
    public BoardDto() { }

    // 2. 모든 필드 생성자 (O)
    public BoardDto(int bno, String btitle) {
        this.bno = bno;
        this.btitle = btitle;
    }

    // 3. getter and setter (O)
    // ... (생략)
}
```
> Day02 실습의 DTO는 이미 모든 조건을 만족하므로 수정할 필요가 없습니다.

### Step 2: `BoardController` 수정하기

Day02에 만든 `BoardController`의 각 메소드를 아래 가이드에 따라 수정해 보세요. 데이터를 받는 부분을 추가하는 것이 핵심입니다.

`example/day02/_3실습/BoardController.java` 파일을 열고 수정합니다.

#### 1. 글쓰기 (POST)

- **요청**: 클라이언트가 JSON 형태로 `{ "btitle" : "새 게시물 제목" }` 과 같은 데이터를 보냅니다.
- **수정**: `@RequestBody`를 사용해서 JSON 데이터를 `BoardDto`로 받습니다.

```java
// 기존 코드
// @PostMapping("exam1/board")
// @ResponseBody
// public boolean exam1(){ ... }

// 수정 코드
@PostMapping("exam1/board")
@ResponseBody
public boolean writeBoard(@RequestBody BoardDto boardDto) {
    System.out.println("BoardController.writeBoard");
    System.out.println("boardDto = " + boardDto);
    // TODO: 나중에 여기서 DAO를 호출해서 DB에 저장합니다.
    return true;
}
```

#### 2. 전체 글 조회 (GET)

- 전체 조회는 보통 요청 데이터가 필요 없으므로 Day02 코드 그대로 둡니다.

#### 3. 개별 글 조회 (GET)

- **요청**: 클라이언트가 쿼리 스트링으로 `?bno=1` 처럼 특정 글 번호를 보냅니다.
- **수정**: `@RequestParam`을 사용해서 `bno`를 받습니다.

```java
// 기존 코드 (개별 조회 기능이 없었으므로 새로 만듭니다)
// @GetMapping("exam1/board/view") ...

// 수정 코드 (URL을 좀 더 RESTful하게 변경)
@GetMapping("exam1/board/view") // 또는 @GetMapping("exam1/board")
@ResponseBody
public BoardDto viewBoard(@RequestParam int bno) {
    System.out.println("BoardController.viewBoard");
    System.out.println("bno = " + bno);
    // TODO: 나중에 여기서 bno로 DB에서 데이터를 찾아 DTO로 반환합니다.
    BoardDto mockDto = new BoardDto(bno, "임시 제목");
    return mockDto;
}
```

#### 4. 글 수정 (PUT)

- **요청**: 클라이언트가 JSON 형태로 `{ "bno": 1, "btitle" : "수정된 제목" }` 과 같이 수정할 글 번호와 내용을 보냅니다.
- **수정**: `@RequestBody`를 사용해서 JSON 데이터를 `BoardDto`로 받습니다.

```java
// 기존 코드
// @PutMapping("exam1/board")
// @ResponseBody
// public boolean exam4(){ ... }

// 수정 코드
@PutMapping("exam1/board")
@ResponseBody
public boolean updateBoard(@RequestBody BoardDto boardDto) {
    System.out.println("BoardController.updateBoard");
    System.out.println("boardDto = " + boardDto);
    // TODO: 나중에 여기서 DTO 정보로 DB 데이터를 수정합니다.
    return true;
}
```

#### 5. 글 삭제 (DELETE)

- **요청**: 클라이언트가 쿼리 스트링으로 `?bno=1` 처럼 삭제할 글 번호를 보냅니다.
- **수정**: `@RequestParam`을 사용해서 `bno`를 받습니다.

```java
// 기존 코드
// @DeleteMapping("exam1/board")
// @ResponseBody
// public int exam5(){ ... }

// 수정 코드
@DeleteMapping("exam1/board")
@ResponseBody
public boolean deleteBoard(@RequestParam int bno) {
    System.out.println("BoardController.deleteBoard");
    System.out.println("bno = " + bno);
    // TODO: 나중에 여기서 bno로 DB 데이터를 삭제합니다.
    return true;
}
```

### Step 3: Talend API Tester로 테스트하기

이제 수정한 API가 잘 동작하는지 테스트해봅시다.

1.  **서버 실행**: `day02/_3실습/AppStart.java`를 실행합니다.
2.  **Talend API Tester 실행**

#### 테스트 1: 글쓰기 (POST)
- `Method`: `POST`
- `URL`: `http://localhost:8080/exam1/board`
- `Headers`: `Content-Type` : `application/json`
- `Body`:
  ```json
  {
      "btitle" : "새로운 글 제목입니다."
  }
  ```
- **결과**: `true`가 반환되고, IntelliJ 콘솔에 `BoardController.writeBoard`, `boardDto = BoardDto{bno=0, btitle='새로운 글 제목입니다.'}` 가 출력되면 성공!

#### 테스트 2: 개별 글 조회 (GET)
- `Method`: `GET`
- `URL`: `http://localhost:8080/exam1/board/view?bno=10`
- **결과**: `{"bno":10,"btitle":"임시 제목"}` 이 반환되고, 콘솔에 `bno = 10`이 출력되면 성공!

#### 테스트 3: 글 수정 (PUT)
- `Method`: `PUT`
- `URL`: `http://localhost:8080/exam1/board`
- `Headers`: `Content-Type` : `application/json`
- `Body`:
  ```json
  {
      "bno" : 10,
      "btitle" : "10번 글 제목 수정!"
  }
  ```
- **결과**: `true`가 반환되고, 콘솔에 `boardDto = BoardDto{bno=10, btitle='10번 글 제목 수정!'}` 이 출력되면 성공!

#### 테스트 4: 글 삭제 (DELETE)
- `Method`: `DELETE`
- `URL`: `http://localhost:8080/exam1/board?bno=10`
- **결과**: `true`가 반환되고, 콘솔에 `bno = 10`이 출력되면 성공!

---

이 가이드를 통해 Day03의 핵심 내용을 학습하고 실습까지 완료하셨기를 바랍니다. 이제 클라이언트의 요청을 자유자재로 다룰 수 있게 되셨을 겁니다. 수고하셨습니다!
