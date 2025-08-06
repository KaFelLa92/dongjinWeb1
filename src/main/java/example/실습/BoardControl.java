package example.실습;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class BoardControl {

    // 1. post 매핑
    // URL /board
    // 기능 설명 : 내용과 작성자 입력받아 저장
    // 매개변수 : String bcontent , String bwriter 또는 BoardDto
    // 반환 불리언
    // true 저장 성공 false 저장실패
    @PostMapping("/board")
    // @ResponseBody   // 반환하고 싶으면 아시죠? 레스트컨트롤러로 대체
    public boolean boardWrite(@RequestBody BoardDto boardDto){
        System.out.println("BoardControl.boardWrite");
        System.out.println("boardDto = " + boardDto);
        // 매개변수 설정 및 객체화
        BoardDto result = new BoardDto(boardDto.getBcontent(), boardDto.getBwriter());
        // 객체화된 dto를 반환
        return true;
    } // func1 end

    // 2. get 맵핑
    // DB에 저장된 모든 게시물 정보(dto) 호출
    //  [   { bno : 1 , bcontent : "안녕하세요" , bwriter : "유재석"  },
    //      { bno : 2 , bcontent : "안녕하세요2" , bwriter : "강호동"  },   ]
    @GetMapping("/board")
    public ArrayList<BoardDto> boardPrint (){
        ArrayList<BoardDto> list = new ArrayList<>();
        return list;
    }

    // 3. get 맵핑
    // URL /board/detail
    // 조회할 번호를 받아 게시물 정보 호출
    //{ bno : 1 , bcontent : "안녕하세요" , bwriter : "유재석"  }
    @GetMapping("/board/detail")
    public BoardDto boardDetail(@RequestParam int bno){
        // 객체화
        BoardDto boardDto = new BoardDto();
        return boardDto;
    }

    // 4. DELETE 맵핑
    // 삭제할 번호 입력받아 삭제
    // true 수정 성공 false 수정 실패
    @DeleteMapping
    public boolean boardDelete(@RequestParam int bno){
        // 객체화

        // 객체화된 dto 반환
        return false;
    }

    // 5. PUT 맵핑
    // 수정할 번호와 수정할 내용 입력받아 수정
    // true 수정 성공 false 수정 실패
    @PutMapping
    @ResponseBody
    public boolean boardUpdate(@RequestBody int bno , String bcontent){
        return false;
    }




}   //  class end

