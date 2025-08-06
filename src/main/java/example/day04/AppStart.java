package example.day04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppStart {
    public static void main(String[] args) {
        // AppStart 클래스는 항상 패키지 상위에 있을 것.
        SpringApplication.run(AppStart.class);
        // +
    }
}
