package example.day01;

import example.day01.controller.BoardController;
import example.day01.model.dto.BoardDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        // main 함수는 Spring Application 을 실행하는 역할만 담당합니다.
        SpringApplication.run(AppStart.class, args);
    } // main end
} // class end
