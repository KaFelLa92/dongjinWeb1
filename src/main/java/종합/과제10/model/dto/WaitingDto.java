package 종합.과제10.model.dto;

public class WaitingDto {
    // 1. 멤버변수
    private int wno;
    private String wnumber;
    private int wcount;

    // 2. 생성자
    // PK 있는 거, 없는 거 하나씩 만든다.
    public WaitingDto(int wno, String wnumber, int wcount) {
        this.wno = wno;
        this.wnumber = wnumber;
        this.wcount = wcount;
    }

    public WaitingDto(String wnumber, int wcount) {
        this.wnumber = wnumber;
        this.wcount = wcount;
    }

    public WaitingDto() {
    }

    public WaitingDto(int wno, int wcount) {
        this.wno = wno;
        this.wcount = wcount;
    }

    public WaitingDto(int wno) {
        this.wno = wno;
    }

    // 3. 메소드(getter and setter , toString)

    public int getWno() {
        return wno;
    }

    public void setWno(int wno) {
        this.wno = wno;
    }

    public String getWnumber() {
        return wnumber;
    }

    public void setWnumber(String wnumber) {
        this.wnumber = wnumber;
    }

    public int getWcount() {
        return wcount;
    }

    public void setWcount(int wcount) {
        this.wcount = wcount;
    }

    @Override
    public String toString() {
        return "WaitingDto{" +
                "wno=" + wno +
                ", wnumber='" + wnumber + '\'' +
                ", wcount=" + wcount +
                '}';
    }
} // class end
