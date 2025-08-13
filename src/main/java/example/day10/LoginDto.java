package example.day10;

import lombok.*;

// 롬복 어노테이션
@NoArgsConstructor // 빈 생성자
@AllArgsConstructor // 전체 매개변수 생성자
@Data // 게터 세터 투스트링 포함된 어노테이션 @Data
// @Getter @Setter @ToString // 게터 세터 투스트링
public class LoginDto {
    private int mno;        // 회원번호
    private String mid;     // 회원ID
    private String mpw;     // 회원PW
}
