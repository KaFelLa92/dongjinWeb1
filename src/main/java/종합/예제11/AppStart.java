package 종합.예제11;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // 스프링부트 실행하는 어노테이션
public class AppStart {
    public static void main(String[] args) {
        // 직접적으로 view 실행이 아닌 스프링 (부트포함) 실행
        // + 스프링 실행하면 프로젝트내 resources 폴더의 HTML/CSS/JS 프론트엔드 싹 등록한다.
        SpringApplication.run(AppStart.class);  // 스프링 실행하는 명령어
    }
}
