package 종합.실습5.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class WaitDto {
    private int wno;
    private String wnumber;
    private int wcount;
    private String wdate;
}
