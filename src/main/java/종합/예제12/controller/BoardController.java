package 종합.예제12.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import 종합.예제12.model.dto.BoardDto;
import 종합.예제12.service.BoardService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("/board") // + 공통 URL
public class BoardController {
    @Autowired     BoardService boardService;

    // 1. 게시물 등록
    // HTTP 정의 : POST /board application/json
    @PostMapping("") // localhost:8080/board
    public boolean boardWrite(@RequestBody BoardDto boardDto){
        System.out.println("BoardController.boardWrite");
        System.out.println("boardDto = " + boardDto);

        boolean result = boardService.boardWrite( boardDto );
        return result;
    } // func end

    // 2. 전체 조회
    @GetMapping("")
    public List<BoardDto> boardPrint(){
        System.out.println("BoardController.boardPrint");
        List<BoardDto> result = boardService.boardPrint();
        return result;
    }

    // 3. 특정 조회
    // localhost:8080/board/find?bno=1
    @GetMapping("/find") // // localhost:8080/board/find?bno=1
    public BoardDto boardFind(@RequestParam int bno){
        System.out.println("BoardController.boardFind");
        BoardDto result = boardService.boardFind(bno);
        return result;
    }

    // 4. 게시물 삭제
    // 탈란드에서 localhost:8080/board/find?bno=1 하면 삭제
    @DeleteMapping("")
    public boolean boardDelete(@RequestParam int bno){
        System.out.println("BoardController.boardDelete");
        System.out.println("bno = " + bno);
        boolean result = boardService.boardDelete(bno);
        return result;
    }
    
    // 5. 게시물 수정
    // { "bno" : 1 , "bcontent" : "안녕못하오" }
    @PutMapping("")
    public boolean boardUpdate(@RequestBody BoardDto boardDto){
        System.out.println("BoardController.boardUpdate");
        System.out.println("boardDto = " + boardDto);
        boolean result = boardService.boardUpdate(boardDto);
        return result;
    }

} // class end
