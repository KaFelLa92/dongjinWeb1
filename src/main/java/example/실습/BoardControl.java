package example.실습;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class BoardControl {

    // 1. post 매핑
    // URL /board
    // 내용과 작성자 입력받아 저장
    // String bcontent , bwriter BoardDto
    @PostMapping("/board")
    @ResponseBody
    public boolean boardWrite(){
        System.out.println("BoardControl.boardWrite");
        return false;
    }
}
