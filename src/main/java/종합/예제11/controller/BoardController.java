package 종합.예제11.controller;

import org.springframework.web.bind.annotation.*;
import 종합.예제11.model.dao.BoardDao;
import 종합.예제11.model.dto.BoardDto;

import java.util.ArrayList;

@RestController // 싱글톤 기능을 보유한 어노테이션 +@Component
public class BoardController {

    // (*) dao 싱글톤 가져오기
    private BoardDao boardDao = BoardDao.getInstance();

    // (1) 등록 기능 구현 : POST
    @PostMapping ("/board") // URL 적어주기
    // @ResponseBody   // 반환값 있으면 리스폰스바디, @RestController 있으니 생략 가능
    public boolean boardWrite(@RequestBody BoardDto boardDto){
        // 1. 여러가지 유효성검사 (패스~)
        // 2. 데이터문제 없으면 묶음(객체)하나로 만들기
        // --> 주의할점 매개변수와 동일한 생성자가 존재하지 않으면 오류 발생한다.
        // BoardDto boardDto = new BoardDto( 0 , bcontent , bwriter );
        // @RequestBody로 처리하기
        // 3. 객체화 된 dto를 dao에게 전달후 결과를 받는다.
        boolean result = boardDao.boardWrite( boardDto );
        // 4. 결과를 view에게 리턴한다.
        return result;
    }

    // (2) 전체조회 기능 구현 : GET
    @GetMapping ("/board")
    // @ResponseBody
    public ArrayList<BoardDto> boardPrint( ){
        // - 유효성검사 ~ // - 매개변수 ~
        // 3. dao에게 전달후 결과를 받는다.
        ArrayList<BoardDto> result = boardDao.boardPrint( );
        // 4. 결과를 view에게 리턴한다.
        return result;
    }

    // (3) 삭제 기능 구현
    // http://localhost:8080/board?bno=1 talend로 체크할 때, 쿼리스트링으로 bno 넘버 매겨서 확인.
    // http://localhost:8080/board 다시 보드 GET해보면, 삭제 확인 가능
    @DeleteMapping ("/board")
    public boolean boardDelete( @RequestParam int bno ){ // 쿼리스트링 쓰므로 리퀘스트파램
        // 1.유효성검사2.객체화
        // 3. dao에게 삭제할번호 전달후 결과를 받는다.
        boolean result = boardDao.boardDelete( bno );
        // 4. 결과를 view에게 리턴한다.
        return result;
    }

    // (4) 수정 기능 구현
    // body에  { "bno" : 3 , "bcontent" : "반가워유" } 입력 후 PUT
    // { "bno": 3, "bcontent": "반가워유", "bwriter": "박명수" } 이렇게 변경됨
    @PutMapping ("/board")
    public boolean boardUpdate( @RequestBody BoardDto boardDto ){
        // 1.유효성검사
        // 2.객체화<선택, 속성이 2개이상 이면서 하나의 dto 표현 가능하면 권장>
        // BoardDto boardDto = new BoardDto( bno , bcontent , null );
        // 리퀘스트파램이 처리해주실거야
        // 3. dao에게 삭제할번호 전달후 결과를 받는다.
        boolean result = boardDao.boardUpdate( boardDto );
        // 4. 결과를 view에게 리턴한다.
        return result;
    }

}
