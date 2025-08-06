package 종합.예제11.model.dto;

public class BoardDto {
    // 1. 멤버변수
    private int bno;
    private String bcontent;
    private String bwriter;

    // 2. 생성자
    // 1) 빈 생성자 : @Request 사용한다.
    public BoardDto() {
    }

    // 2) 전체 생성자
    public BoardDto(int bno, String bcontent, String bwriter) {
        this.bno = bno;
        this.bcontent = bcontent;
        this.bwriter = bwriter;
    }

    // 3. getter and setter , toString
    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    public String getBcontent() {
        return bcontent;
    }

    public void setBcontent(String bcontent) {
        this.bcontent = bcontent;
    }

    public String getBwriter() {
        return bwriter;
    }

    public void setBwriter(String bwriter) {
        this.bwriter = bwriter;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "bno=" + bno +
                ", bcontent='" + bcontent + '\'' +
                ", bwirter='" + bwriter + '\'' +
                '}';
    }
}
