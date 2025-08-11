package example.day08._2MVC.controller;

import example.day08._2MVC.service.MvcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// JS Fetch로부터 요청을 받음 (JSON 타입으로 연락)
@RestController // [1] Controller 빈 등록
public class MvcController {
    @Autowired // [2] Service 빈 주입
    private MvcService mvcService;
    
    // [3] 기능 처리
    @GetMapping("/day08/mvc")
    public void method(){
        System.out.println("MvcController.method"); // soutm 확인
        mvcService.method(); // 서비스 메소드 호출
    }


} // class end
