package example.실습4.model.dto;

import lombok.*;

// 롬복 어노테이션으로 대체
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WaitDto {
    // 멤버변수
    private int wno;
    private String wnumber;
    private int wcount;
}
