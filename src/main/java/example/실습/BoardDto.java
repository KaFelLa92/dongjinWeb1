package example.실습;

public class BoardDto {
    // 1. 멤버변수
    private int bno;
    private String bcontent;
    private String bwriter;

    // 2. 생성자
    // 2-1) 전체
    public BoardDto(int bno, String bcontent, String bwriter) {
        this.bno = bno;
        this.bcontent = bcontent;
        this.bwriter = bwriter;
    }

    // 2-2) Post용
    public BoardDto(String bcontent, String bwriter) {
        this.bcontent = bcontent;
        this.bwriter = bwriter;
    }

    // 2-3) 개별조회, 삭제용
    public BoardDto(int bno) {
        this.bno = bno;
    }

    // 2-4) 수정용
    public BoardDto(int bno, String bcontent) {
        this.bno = bno;
        this.bcontent = bcontent;
    }


    // 3. 메소드 (getter and setter , toString)
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
                ", bwriter='" + bwriter + '\'' +
                '}';
    }
}
