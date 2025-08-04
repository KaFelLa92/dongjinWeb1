package example.day01.controller;

import example.day01.model.dao.BoardDao;
import example.day01.model.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class BoardController {

    @Autowired
    private BoardDao boardDao;

    // (1) 등록 기능 구현
    @PostMapping("/day01/board")
    public boolean boardWrite(@RequestBody BoardDto boardDto ){
        return boardDao.boardWrite( boardDto );
    }

    // (2) 전체조회 기능 구현
    @GetMapping("/day01/board")
    public ArrayList<BoardDto> boardPrint( ){
        return boardDao.boardPrint( );
    }

}
