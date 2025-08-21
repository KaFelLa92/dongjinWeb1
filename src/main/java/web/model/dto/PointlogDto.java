package web.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PointlogDto {
    private int plno;
    private int mno;
    private int plpoint;
    private String plcomment;
    private String pldate;
}
