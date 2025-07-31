package example.day02._2REST;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller // @Component 포함됨
public class RestController2 {
    // 싱글톤 대신에 @Component 이용한 인스턴스 자동 생성
    // * 어노테이션은 주석 제외 클래스/메소드와 공백 없어야한다

    // [ 반환타입 있는 REST ]
        // @ResponseBody : 자바는 int 타입을 알지만, 웹 HTTP는 int 타입을 모름
            // 역할 : 자바에서 사용하는 타입들을 자동으로 HTTP(웹)이 이해하는 타입으로 변환해준다.

    // 1. 100 반환하는 메소드
    // Talend API : [Method] GET , [Scheme] http://localhost:8080/day02/task
    @GetMapping ("/day02/task") // @XXXMapping("URL주소정의");
    @ResponseBody // 자바에서 반환하는 int 타입을 HTTP가 이해하는 타입으로 자동 반환
    public int method1( ){
        System.out.println("RestController2.method1"); // soutm
        return 100;
    } // method end
    
    // 2. 문자열 반환하는 메소드
    @GetMapping("/day02/task2") // 주의할점 : 서버내 동일한 주소 불가능 ,
    // Talend API : [Method] GET , [Scheme] http://localhost:8080/day02/task2
    @ResponseBody
    public String method2(){
        System.out.println("RestController2.method2");
        return "스프링이 보내온 메시지";
    } // func end

    // 3. MAP 타입 반환하는 메소드
    @PostMapping("/day02/task") // 주의할점 : 서버내 동일한 주소 불가능하지만, REST 다르면 가능
    @ResponseBody
    public Map<String , Integer> method3(){
        System.out.println("RestController2.method3");
        Map<String , Integer> map = new HashMap<>(); // 임의데이터
        map.put("강호동" , 100);
        map.put("유재석" , 95);
        return map;
    } // func end

    // 4. boolean 타입 반환하는 메소드
    @PutMapping("/day02/task")
    @ResponseBody // 반환하려면 이 어노테이션 꼭 써라
    public boolean method4(){
        System.out.println("RestController2.method4");
        return false;
    } // func end
    
    // 5. DTO 타입 반환하는 메소드
    // Talend API : [Method] DELETE , [Scheme] http://localhost:8080/day02/task2
    @DeleteMapping("/day02/task") // CRUD 다르면 주소 같아도 메소드 가능
    @ResponseBody // 반환하고 싶으면 꼭 써라
    public TaskDto method5(){
        System.out.println("RestController2.method5");
        TaskDto taskDto = new TaskDto("유재석" , 95); // 임의데이터
        return taskDto;
    } // func end
    
    // 6. ArrayList 타입 반환하는 메소드
    @GetMapping("/day02/task3")
    @ResponseBody // HTTP(웹)은 List 모름. 그래서 list 타입을 HTTP가 이해하는 타입으로 변환
    public List<TaskDto> method6(){
        System.out.println("RestController2.method6");
        ArrayList<TaskDto> list = new ArrayList<>(); // 임의데이터
        list.add(new TaskDto("유재석", 100));
        list.add(new TaskDto("강호동" , 78));
        return list;
    }

} // class end
