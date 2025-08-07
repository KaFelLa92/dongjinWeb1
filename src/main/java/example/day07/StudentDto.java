package example.day07;

// 롬복 : 설계에서 자주 사용되는 코드 자동 생성
// 롬복 설치
// 1) (환경)인텔리제이 설치 [파일] -> [설정] -> [플러그인] -> lombok 검색 -> 설치
// 2) (프로젝트) 그레이들 설치 : https://start.spring.io/ 에서 롬복 검색
// 디펜던시에서 롬복 설정 후, build.gradle의 dependencies에  아래 두 줄 추가
// compileOnly 'org.projectlombok:lombok'
// annotationProcessor 'org.projectlombok:lombok'

import lombok.*;

@NoArgsConstructor  // 빈 생성자(컴파일/실행 시) 자동 생성
@AllArgsConstructor // 모든 매개변수 생성자(컴파일/실행 시) 자동 생성
@Getter             // 모든 멤버변수 getter 메소드
@Setter             // 모든 멤버변수 setter 메소드
@ToString           // 객체 조회시 주소값 대신 문자열 출력 메소드
public class StudentDto {
    // 1. 멤버변수
    private int sno;        // 번호
    private String sname;   // 이름
    private int skor;       // 국어 점수
    private int smath;      // 수학 점수
    private String sdate;   // 등록일
    
    // 2. 생성자 (빈, 꽉 하나씩)
//    public StudentDto(int sno, String sname, int skor, int smath, String sdate) {
//        this.sno = sno;
//        this.sname = sname;
//        this.skor = skor;
//        this.smath = smath;
//        this.sdate = sdate;
//    }
//
//    public StudentDto() {
//    }

    // 3. getter and setter , toString
//    public int getSno() {
//        return sno;
//    }
//
//    public void setSno(int sno) {
//        this.sno = sno;
//    }
//
//    public String getSname() {
//        return sname;
//    }
//
//    public void setSname(String sname) {
//        this.sname = sname;
//    }
//
//    public int getSkor() {
//        return skor;
//    }
//
//    public void setSkor(int skor) {
//        this.skor = skor;
//    }
//
//    public int getSmath() {
//        return smath;
//    }
//
//    public void setSmath(int smath) {
//        this.smath = smath;
//    }
//
//    public String getSdate() {
//        return sdate;
//    }
//
//    public void setSdate(String sdate) {
//        this.sdate = sdate;
//    }

//    @Override
//    public String toString() {
//        return "StudentDto{" +
//                "sno=" + sno +
//                ", sname='" + sname + '\'' +
//                ", skor=" + skor +
//                ", smath=" + smath +
//                ", sdate='" + sdate + '\'' +
//                '}';
//    }
} // dto end
