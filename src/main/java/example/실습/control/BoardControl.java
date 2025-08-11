package example.실습.control;

import example.실습.model.dto.BoardDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board")
public class BoardControl {

    // 1. post 매핑
    // URL /board
    // 기능 설명 : 내용과 작성자 입력받아 저장
    // 매개변수 : String bcontent , String bwriter 또는 BoardDto
    // 반환 불리언
    // true 저장 성공 false 저장실패
    // ***map***으로 처리
    @PostMapping
    // @ResponseBody   // 반환하고 싶으면 아시죠? => 레스트컨트롤러로 대체
    public boolean boardWrite(@RequestBody BoardDto boardDto){
        System.out.println("BoardControl.boardWrite");
        System.out.println("boardDto = " + boardDto);
        // 객체화된 dto를 반환
        return true;
    } // func1 end

    // 2. get 맵핑
    // DB에 저장된 모든 게시물 정보(dto) 호출
    //  [   { bno : 1 , bcontent : "안녕하세요" , bwriter : "유재석"  },
    //      { bno : 2 , bcontent : "안녕하세요2" , bwriter : "강호동"  },   ]
    @GetMapping
    public List<BoardDto> boardPrint (){
        System.out.println("BoardControl.boardPrint");
        ArrayList<BoardDto> list = new ArrayList<>();
        list.add(new BoardDto(1 , "안녕하세요" , "유재석"));
        list.add(new BoardDto(2, "안녕하세요2" , "강호동"));
        return list;
    }

    // 3. get 맵핑
    // URL /board/detail
    // 조회할 번호를 받아 게시물 정보 호출
    //{ bno : 1 , bcontent : "안녕하세요" , bwriter : "유재석"  }
    @GetMapping("/detail")
    public BoardDto boardDetail(@RequestParam int bno){
        System.out.println("BoardControl.boardDetail");
        // 객체화
        BoardDto boardDto = new BoardDto();
        boardDto.setBno(bno);
        boardDto.setBcontent("안녕하세요");
        boardDto.setBwriter("유재석");
        return boardDto;
    }

    // 4. DELETE 맵핑
    // 삭제할 번호 입력받아 삭제
    // true 수정 성공 false 수정 실패
    // http://localhost:8080/board?bno=1 하면 넘버 삭제
    @DeleteMapping
    public boolean boardDelete(@RequestParam int bno){
        System.out.println("BoardControl.boardDelete");
        // 객체화된 dto 반환
        return true;
    }

    // 5. PUT 맵핑
    // 수정할 번호와 수정할 내용 입력받아 수정
    // true 수정 성공 false 수정 실패
    // http://localhost:8080/board
    // Body에 { "bno": 1, "bcontent" : "수정된 내용" } 작성
    @PutMapping
    public boolean boardUpdate(@RequestBody BoardDto boardDto){
        System.out.println("BoardControl.boardUpdate");
        return true;
    }




}   //  class end

