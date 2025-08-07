package example.day07;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// @ : 어노테이션이란? 추가적인 정보를 제공하는 메타데이터 역할 , 실질적인 기능(스프링)
@RestController // @Controller (+@Component) +@ResponseBody
// @Component : 스프링 컨테이너(메모리)에 빈(객체) 등록/생성, 싱글톤과 유사
// @Controller : HTTP 요청과 응답처리
// @ResponseBody : HTTP 응답 자료의 *자동* (JSON) 타입 변환
@RequestMapping("/student")
// @RequestMapping("/URL") : 지정한 클래스내 모든 메소드들의 공통 url
public class StudentController {

    // (*) 싱글톤 호출
    private StudentDao studentDao = StudentDao.getInstance();

    // 1. 저장
    @PostMapping ("/save") // localhost:8080/student/save
    // @리퀘스트맵핑으로 클래스에서 주소 앞자리 잡아준다
    // @PostMapping : 등록 요청 매핑
    public boolean save(@RequestBody StudentDto studentDto){
        System.out.println("StudentController.save"); // soutm
        System.out.println("studentDto = " + studentDto);
        // dao 연동
        boolean result = studentDao.save(studentDto);
        return result;
    }

    // 2. 전체 조회
    @GetMapping ("/find") // localhost:8080/student/find
    // @ GetMapping : 조회 요청 매핑
    public List<StudentDto> find(){
        System.out.println("StudentController.method2");
        List<StudentDto> list = studentDao.find();  // dao와 연동
        return list;
    }

}
