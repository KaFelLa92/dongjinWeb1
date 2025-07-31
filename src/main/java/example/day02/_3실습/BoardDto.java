package example.day02._3실습;

public class BoardDto {
    // 1. 멤버변수
    private int bno;
    private String btitle;

    // 2. 생성자
    // 2-1) 매개변수 없는 생성자
    public BoardDto() {
    }

    // 2-2) 매개변수 있는 생성자
    public BoardDto(int bno, String btitle) {
        this.bno = bno;
        this.btitle = btitle;
    }

    // 3. getter and setter , toString
    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public int getBno() {
        return bno;
    }

    public void setBno(int bno) {
        this.bno = bno;
    }

    @Override
    public String toString() {
        return "BoardDto{" +
                "bno=" + bno +
                ", btitle='" + btitle + '\'' +
                '}';
    }
}
