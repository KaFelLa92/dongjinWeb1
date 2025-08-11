package example.day08._2MVC.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
// @Component는 안 씀. bean으로 안 쓸 거라서.
public class MvcDto {
    private String var1;
}
