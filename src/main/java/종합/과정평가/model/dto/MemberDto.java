package 종합.과정평가.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class MemberDto {
    // 멤버변수
    private int custno;
    private String custname;
    private String phone;
    private String address;
    private String joindate;
    private String grade;
    private String city;
}
