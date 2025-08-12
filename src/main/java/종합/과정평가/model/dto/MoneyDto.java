package 종합.과정평가.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class MoneyDto {
    // 멤버변수
    private int custno;
    private int salenol;
    private int pcost;
    private int amount;
    private int price;
    private String pcode;
    private String date;
}
