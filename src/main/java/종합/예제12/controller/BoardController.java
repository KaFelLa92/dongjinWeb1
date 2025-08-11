package 종합.예제12.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import 종합.예제12.model.dto.BoardDto;
import 종합.예제12.service.BoardService;

import java.util.ArrayList;

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
//    @GetMapping("")
//    public ArrayList<BoardDto> boardPrint(){
//        System.out.println("BoardController.boardPrint");
//        boolean result
//
//    }

    // 3. 특정 조회

    // 4. 게시물 삭제
    
    // 5. 게시물 수정

} // class end
