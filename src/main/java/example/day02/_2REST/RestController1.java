package example.day02._2REST;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller // 해당 클래스에 @Controller 어노테이션 주입
// 1. @Component : Spring 컨테이너(메모리)에 bean(객체)을 등록/생성한다. < 싱글톤 대신 사용 >
// 2. Spring Controller는 기본적으로 HTTP(웹 서블릿) 통신 지원한다.
public class RestController1 {
    // 싱글톤 생략 : @Controller -> @Component 포함되어 자동으로 싱글톤처럼 하나의 객체를 생성함.
    // REST란? CRUD 기능을 HTTP로 제공하는 아키텍처/기술/구조
    // Spirng REST란? @PostMapping , @GetMapping , @PutMapping , @DeleteMapping 제공함으로 웹 기술 제공
    // REST 테스터 : Talend API Tester - Free Edition
        // 1. https://chromewebstore.google.com/detail/talend-api-tester-free-ed/aejoelaoggembcahagimdiliamlcdmfm?hl=ko&utm_source=ext_sidebar
        // 2. 크롬에 추가
        // 3. 크롬 확장 프로그램에서 Talend API 실행
    // 1) 등록 : CREATE -> @PostMapping
    @PostMapping
    public void 등록하기(){
        System.out.println("RestController1.등록하기"); //  soutm 자동 완성
    }

    // 2) 조회 : READ -> @GetMapping
    @GetMapping     // Talend API : [METHOD] GET , [Scheme] : http://localhost:8080
    public void 조회하기(){
        System.out.println("RestController.조회하기");
    }

    // 3) 수정 : UPDATE -> @PutMapping
    @PutMapping
    public void 수정하기(){
        System.out.println("RestController.수정하기");
    }

    // 4) 삭제 : DELETE -> @DeleteMapping
    @DeleteMapping
    public void 삭제하기(){
        System.out.println("RestController.삭제하기");
    }


} // class end
