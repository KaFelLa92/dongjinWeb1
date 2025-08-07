package example.실습4.controller;

import example.실습4.model.dao.WaitDao;
import example.실습4.model.dto.WaitDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/waiting")
public class WaitControl {

    // (*) 싱글톤 호출
    private WaitDao waitDao = WaitDao.getInstance();

    // 1. 대기번호등록
    @PostMapping ("/add") // localhost:8080/waiting/add
    public boolean waitAdd(@RequestBody WaitDto waitDto){
        System.out.println("WaitControl.waitAdd");
        System.out.println("waitDto = " + waitDto);
        // DAO 연동
        // 웨이트다오클래스의 웨이트애드메소를 활용해, dto의 정보를 담는다
        boolean result = waitDao.waitAdd(waitDto);
        return result;
    } // func end
    
    // 2. 대기현황전체조회
    @GetMapping ("/print") // localhost:8080/waiting/print
    public List<WaitDto> waitPrint(){
        System.out.println("WaitControl.waitPrint");
        // DAO 연동
        // WaitDto의 객체를 웨이트다오의 프린트메소드를 활용하여 쌓는다. JSP로 간다.
        List<WaitDto> list = waitDao.waitPrint();
        return list;
    } // func end

} // class end
