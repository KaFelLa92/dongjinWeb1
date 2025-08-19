package web.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {
    // 멤버변수는 기본적으로 DB 테이블과 동일하게
    private int pno;
    private String pname;
    private int pprice;
    private String pcomment;
    private String pdate;
    private double plat;
    private double plng;
    private int mno;
    // * 진짜 파일은 build에 저장되고, 파일의 이름만 DB에 저장됨
    // + 커스텀 정보 포함
    // 1) 업로드에 사용할 multipartFile 객체 list 타입으로 첨부파일 받아오기
    private List<MultipartFile> uploads;
    // 2) 조회시 업로드된 파일명을 문자열로 조회하기. 파일명만 DB에 저장됨. 그래서 문자열 배열로 저장
    private List<String> images;
    // 3) 판매자의 아이디
    private int mid;

}
