package example.day02._2REST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);
        // 1. SpringApplication 클래스
        // 2. .run( ) : static 선언된 스프링 실행 함수
        // 3. ( 현재클래스.class ) :
        // 현재클래스를 매개변수 전달시  @SpringBootApplication 동작한다.

    } // main end
} // class end
