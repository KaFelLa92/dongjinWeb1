package 종합.과정평가.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter @ToString
public class MemberSaleDto {
    private int custno;
    private String custname;
    private String grade;
    private int sale;
}
