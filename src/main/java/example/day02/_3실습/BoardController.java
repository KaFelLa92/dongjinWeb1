package example.day02._3실습;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller // @Component 포함된 컨트롤러 어노케이션 생성
public class BoardController {

    // 싱글톤은 @Component 인스턴스로 대체되었다
    // 250731 앉은 자리 ip랑 합치면 주소값 192.168.40.186:8080

    /*
    Controller 에서 URL 매핑 찍기

    1. 글쓰기           POST       "/exam1/board"
        요청자료 : x , 응답자료 : true/false

        2. 전체 글 조회      GET        "/exam1/board"
        요청자료 : x , 응답자료 : 임의의 List 타입 ,  [ {bno:'',btitle:''} ,  {bno:'',btitle:''}  ] , 아래 참고

        3. 개별 글 조회      GET        "/exam1/board/view"
        요청자료 : x , 응답자료 : 임의의 MAP  타입  ,  {bno:'',btitle:''} , 아래 참고

        4. 개글 글 수정      PUT        "/exam1/board"
        요청자료 : x , 응답자료 : true 또는 false

        5. 개별 글 삭제      DELETE     "/exam1/board"
        요청자료 : x , 응답자료 : 임의의 삭제한 번호 , 3
     */

    // 1. 글쓰기는 포스트맵핑
    // 리턴값 boolean
    @PostMapping ("exam1/board")    // REST CRUD 어노테이션 옆에 주소값 괄호 안에 스트링 형식으로 써주기
    @ResponseBody // 리턴값 있으면 리스폰스바디
    public boolean exam1(){
        System.out.println("BoardController.exam1");
        return false;
    } // func end

    // 2. 조회는 GetMapping
    // 리턴값 List 타입(전체조회) Dto 생성
    @GetMapping ("exam1/board")
    @ResponseBody
    public List<BoardDto> exam2(){
        System.out.println("BoardController.exam2");
        ArrayList<BoardDto> list = new ArrayList<>();   // 임의데이터
        list.add(new BoardDto());
        list.add(new BoardDto());
        return list;
    } // func end

    // 3. 조회는 GetMapping
    // 리턴값 Map 타입(일회성 데이터. 개별조회)
    @GetMapping ("exam1/board/view")
    @ResponseBody
    public Map<Integer, String> exam3(){
        System.out.println("BoardController.exam3");
        Map<Integer, String> map = new TreeMap<>(); // 키를 기준으로 오름차순되는 트리맵을 인스턴스로
        map.put(3 , "으데 왔는교");
        map.put(1 , "안녕하세요");
        map.put(2 , "반갑습니다");
        return map;
    } // func end

    // 4. 수정은 PutMapping
    // 리턴값 불리언
    @PutMapping ("exam1/board")
    @ResponseBody
    public boolean exam4(){
        System.out.println("BoardController.exam4");
        return false;
    } // func end

    // 5. 삭제는 DeleteMapping
    // 리턴값 인트
    @DeleteMapping ("exam1/board")
    @ResponseBody // 반환 있으면 리스폰스바디 어노테이션
    public int exam5(){
        System.out.println("BoardController.exam5");
        return 3;
    } // func end

} // class end
