
# BoardControl.java 하드코딩 구현 가이드

안녕하세요! `BoardControl.java` 파일의 요구사항을 기반으로 하드코딩 솔루션을 단계별로 안내해 드리겠습니다.

데이터베이스 대신 `ArrayList`를 사용하여 게시물 데이터를 메모리에 저장하고 관리하는 방식으로 구현합니다.

---

### 1단계: 인메모리(In-Memory) 데이터 저장소 설정

먼저 컨트롤러 내부에 게시글을 저장할 `ArrayList`와 게시글 번호를 관리할 `int` 변수를 `static`으로 선언합니다. 이렇게 하면 서버가 실행되는 동안 데이터가 유지됩니다.

```java
@RestController
@RequestMapping("/board")
public class BoardControl {

    // DB 역할: 게시물 DTO들을 저장하는 리스트
    private static List<BoardDto> boardDtoList = new ArrayList<>();
    // 게시물 번호(bno)를 위한 자동 증가 카운터
    private static int bnoCounter = 1;

    // ... 이하 메소드들
}
```

---

### 2단계: `boardWrite` (POST) - 게시물 작성

`@PostMapping`으로 매핑된 `boardWrite` 메소드는 클라이언트로부터 `bcontent`와 `bwriter`가 포함된 `BoardDto` 객체를 받아 `boardDtoList`에 추가합니다.

**요구사항:**
- **URL:** `/board` (POST)
- **기능:** 내용과 작성자를 입력받아 저장
- **반환:** `boolean` (성공: `true`, 실패: `false`)

**구현:**
1.  매개변수로 받은 `BoardDto`에 게시물 번호(`bno`)를 할당합니다.
2.  `bnoCounter`를 1 증가시킵니다.
3.  해당 `BoardDto`를 `boardDtoList`에 추가합니다.
4.  성공적으로 추가되었으므로 `true`를 반환합니다.

```java
@PostMapping
public boolean boardWrite(@RequestBody BoardDto boardDto){
    System.out.println("[POST] BoardControl.boardWrite");
    System.out.println("boardDto = " + boardDto);

    // 1. 새로운 게시물 번호 할당
    boardDto.setBno(bnoCounter++);
    // 2. 리스트에 DTO 추가
    boardDtoList.add(boardDto);

    return true; // 저장 성공
}
```

---

### 3단계: `boardPrint` (GET) - 모든 게시물 조회

`@GetMapping`으로 매핑된 `boardPrint` 메소드는 `boardDtoList`에 저장된 모든 게시물 정보를 반환합니다.

**요구사항:**
- **URL:** `/board` (GET)
- **기능:** 저장된 모든 게시물 정보 호출
- **반환:** `List<BoardDto>`

**구현:**
- 저장소 역할을 하는 `boardDtoList`를 그대로 반환합니다.

```java
@GetMapping
public List<BoardDto> boardPrint() {
    System.out.println("[GET] BoardControl.boardPrint");
    return boardDtoList;
}
```

---

### 4단계: `boardDetail` (GET) - 특정 게시물 조회

`@GetMapping("/detail")`로 매핑된 `boardDetail` 메소드는 `bno`를 받아 해당하는 게시물을 찾아 반환합니다.

**요구사항:**
- **URL:** `/board/detail` (GET)
- **기능:** 조회할 번호를 받아 게시물 정보 호출
- **반환:** `BoardDto`

**구현:**
1.  `for` 루프를 사용해 `boardDtoList`를 순회합니다.
2.  각 `BoardDto`의 `bno`가 요청된 `bno`와 일치하는지 확인합니다.
3.  일치하는 객체를 찾으면 즉시 해당 `BoardDto`를 반환합니다.
4.  리스트 전체를 순회해도 찾지 못하면 `null`을 반환합니다.

```java
@GetMapping("/detail")
public BoardDto boardDetail(@RequestParam int bno){
    System.out.println("[GET] BoardControl.boardDetail");
    System.out.println("bno = " + bno);

    for(BoardDto dto : boardDtoList){
        if(dto.getBno() == bno){
            return dto; // 찾았으면 해당 DTO 반환
        }
    }
    return null; // 못 찾았으면 null 반환
}
```

---

### 5단계: `boardDelete` (DELETE) - 게시물 삭제

`@DeleteMapping`으로 매핑된 `boardDelete` 메소드는 `bno`를 받아 해당하는 게시물을 리스트에서 삭제합니다.

**요구사항:**
- **URL:** `/board` (DELETE)
- **기능:** 삭제할 번호를 입력받아 삭제
- **반환:** `boolean` (성공: `true`, 실패: `false`)

**구현:**
1.  `for` 루프를 사용해 `boardDtoList`를 순회합니다.
2.  `bno`가 일치하는 `BoardDto`를 찾으면 `boardDtoList.remove()`를 사용해 삭제합니다.
3.  삭제에 성공하면 `true`를 반환합니다.
4.  리스트를 모두 순회해도 대상을 찾지 못하면 `false`를 반환합니다.

```java
@DeleteMapping
public boolean boardDelete(@RequestParam int bno){
    System.out.println("[DELETE] BoardControl.boardDelete");
    System.out.println("bno = " + bno);

    for(BoardDto dto : boardDtoList){
        if(dto.getBno() == bno){
            boardDtoList.remove(dto);
            return true; // 삭제 성공
        }
    }
    return false; // 삭제 실패
}
```

---

### 6단계: `boardUpdate` (PUT) - 게시물 수정

`@PutMapping`으로 매핑된 `boardUpdate` 메소드는 수정할 정보가 담긴 `BoardDto`를 받아 기존 게시물을 업데이트합니다.

**요구사항:**
- **URL:** `/board` (PUT)
- **기능:** 수정할 번호와 내용을 입력받아 수정
- **반환:** `boolean` (성공: `true`, 실패: `false`)

**구현:**
1.  매개변수를 `@RequestBody BoardDto boardDto`로 변경하여 수정할 `bno`와 `bcontent`를 한 번에 받습니다.
2.  `for` 루프를 사용해 `boardDtoList`를 순회합니다.
3.  `bno`가 일치하는 `BoardDto`를 찾으면 해당 객체의 `bcontent`와 `bwriter`를 새로운 값으로 업데이트합니다.
4.  업데이트에 성공하면 `true`를 반환합니다.
5.  대상을 찾지 못하면 `false`를 반환합니다.

```java
@PutMapping
public boolean boardUpdate(@RequestBody BoardDto boardDto){
    System.out.println("[PUT] BoardControl.boardUpdate");
    System.out.println("boardDto = " + boardDto);

    for(BoardDto existingDto : boardDtoList){
        if(existingDto.getBno() == boardDto.getBno()){
            // 내용과 작성자 업데이트
            existingDto.setBcontent(boardDto.getBcontent());
            existingDto.setBwriter(boardDto.getBwriter());
            return true; // 수정 성공
        }
    }
    return false; // 수정 실패
}
```

---

### 최종 `BoardControl.java` 전체 코드

아래는 위 가이드를 모두 적용한 전체 코드입니다. 복사해서 사용하세요.

```java
package example.실습.control;

import example.실습.model.dto.BoardDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardControl {

    // DB 역할: 게시물 DTO들을 저장하는 리스트
    private static List<BoardDto> boardDtoList = new ArrayList<>();
    // 게시물 번호(bno)를 위한 자동 증가 카운터
    private static int bnoCounter = 1;

    // 1. post 매핑: 게시물 작성
    @PostMapping
    public boolean boardWrite(@RequestBody BoardDto boardDto){
        System.out.println("[POST] BoardControl.boardWrite");
        System.out.println("boardDto = " + boardDto);

        boardDto.setBno(bnoCounter++);
        boardDtoList.add(boardDto);
        return true;
    }

    // 2. get 맵핑: 모든 게시물 조회
    @GetMapping
    public List<BoardDto> boardPrint (){
        System.out.println("[GET] BoardControl.boardPrint");
        return boardDtoList;
    }

    // 3. get 맵핑: 특정 게시물 상세 조회
    @GetMapping("/detail")
    public BoardDto boardDetail(@RequestParam int bno){
        System.out.println("[GET] BoardControl.boardDetail");
        System.out.println("bno = " + bno);

        for(BoardDto dto : boardDtoList){
            if(dto.getBno() == bno){
                return dto;
            }
        }
        return null;
    }

    // 4. DELETE 맵핑: 게시물 삭제
    @DeleteMapping
    public boolean boardDelete(@RequestParam int bno){
        System.out.println("[DELETE] BoardControl.boardDelete");
        System.out.println("bno = " + bno);

        for(BoardDto dto : boardDtoList){
            if(dto.getBno() == bno){
                boardDtoList.remove(dto);
                return true;
            }
        }
        return false;
    }

    // 5. PUT 맵핑: 게시물 수정
    @PutMapping
    public boolean boardUpdate(@RequestBody BoardDto boardDto){
        System.out.println("[PUT] BoardControl.boardUpdate");
        System.out.println("boardDto = " + boardDto);

        for(BoardDto existingDto : boardDtoList){
            if(existingDto.getBno() == boardDto.getBno()){
                existingDto.setBcontent(boardDto.getBcontent());
                existingDto.setBwriter(boardDto.getBwriter());
                return true;
            }
        }
        return false;
    }
}
```
