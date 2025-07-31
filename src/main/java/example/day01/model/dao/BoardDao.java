package example.day01.model.dao;

import example.day01.model.dto.BoardDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class BoardDao {
    // (*) 싱글톤
    private BoardDao() {
        connect(); // connect() 생성자에 들어가 있어야 등록 가능
    }

    public static final BoardDao instance = new BoardDao();

    public static BoardDao getInstance() {
        return instance;
    }

    // (*) DB 연동
    // 1. 연결할 곳 정보 쓰기
    private String db_url = "jdbc:mysql://localhost:3306/exam10";
    private String db_user = "root";
    private String db_password = "1234";
    private Connection conn;

    // 2. 연동 함수
    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db_url, db_user, db_password);
        } catch (Exception e) {
            System.out.println(e);
        }
    } // func end

    // (1) 등록 기능 구현
    public boolean boardWrite(BoardDto boardDto) {
        // boolean              : 해당 메소드 실행 결과를 true(저장성공) false(저장실패) 반환하기위한 타입
        // BoardDto boardDto    : controller로부터 저장할 값들을 dto로 구성해서 받는 매개변수
        try {
            // 1. SQL 작성한다.
            String sql = "insert into board( bcontent , bwriter) values (? , ?); ";
            // 2. SQL 기재한다.
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 대입
            ps.setString(1, boardDto.getBcontent());   // SQL내 1번 ?에 매개변수로 받은 boardDto의 bcontent 값 대입
            ps.setString(2, boardDto.getBwriter());   // SQL내 2번 ?에 매개변수로 받은 boardDto의 bwriter 값 대입
            // 4. SQL 실행 , insert/update/delete는 .executeUpdate();
            int count = ps.executeUpdate();
            // 5. SQL 결과에 따른 로직/리턴/확인
            if (count >= 1) {
                return true;    // 1개 이상 insert하면 저장 성공
            } else {
                return false;   // 1개 미만 insert면 저장 실패
            }
        } catch (Exception e) {
            System.out.println("[경고] DB 등록 실패" + e);
        } // catch end
        return false;   // catch 못 뚫으면 저장 실패
    } // func end

    // (2) 전체조회 기능 구현
    public ArrayList<BoardDto> boardPrint() {
        // public 이유 : 다른 패키지의 controller가 접근하기 위해
        // public 다른 패키지 접근 vs private 현재 클래스만
        // ArrayList<BoardDto> : 배열 대신에 다양한 편의성 기능을 제공하는 ArrayList 클래스
        // 3 = int , 3 5 7 = int int int --> int[]
        // bcontent/bwriter = BoardDto타입 , bcontent/bwriter bcontent/bwriter bcontent/bwriter = BoardDto[]
        ArrayList<BoardDto> list = new ArrayList<>(); // 조회된 레코드(DTO) 등을 저장할 리스트 선언
        // 어레이리스트는 트라이캐치 밖에 만들어야 변수를 외부로 리턴할 수 있다.
        try {
            // 1. SQL 작성
            String sql = "select * from board";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 대입
            // 4. SQL 실행 , select = executeQuery()
            ResultSet rs = ps.executeQuery(); // select할때만 ResultSet 쓸듯?
            // 5. SQL 결과에 따른 로직/리턴/확인
            // 1) select 조회 결과를 레코드/행/가로줄 하나씩 조회
            while (rs.next()) { // rs.next() : ResultSet 인터페이스가 갖는 결과 테이블에서 다음 레코드 이동 뜻
                // BoardDto boardDto = new BoardDto( rs.getInt("bno"), rs.getString("bcontent") , rs.getString("bwriter"));
                // rs.get타입("가져올속성명or번호")
                // 2) 현재 조회중인 레코드의 속성값 호출해서 dto 만들기
                int bno = rs.getInt("bno");
                String bcontent = rs.getString("bcontent");
                String bwriter = rs.getString("bwriter");
                BoardDto boardDto = new BoardDto(bno, bcontent, bwriter); // 레코드 1개(열3개) -> DTO 1개(멤버변수3개)
                // 3) 생성된 dto를 리스트에 담아주기
                list.add(boardDto);
            } // while end
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;

    } // func end

    // (3) 삭제 기능 구현
    public boolean boardDelete( int bno ) {
        // int bno : 매개변수이면서 삭제할 게시물의 식별(pk) 번호
        try {
            // 1. SQL 작성
            String sql = "delete from board where bno = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 대입
            ps.setInt(1 ,bno); // SQL 문법내 첫번째 ?의 int 타입으로 bno 값 대입

            // 4. SQL 실행 delete = executeUpdate();
            int count = ps.executeUpdate();

            // 5. SQL 결과에 따른 로직/리턴/확인
            if (count == 1){
                return true;    // sql 결과 1이면 성공
            } else {
                return false;   // sql 결과 1 아니면 실패
            }
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return false; // 예외발생시 삭제실패
    } // func end

    // (4) 수정 기능 구현
    public boolean boardUpdate( BoardDto boardDto){
        try{
            // 1. SQL 작성
            String sql = "update board set bcontent = ? where bno = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 대입 , SQL문법내 ? 개수만큼 대입
            ps.setString(1 , boardDto.getBcontent() );    // .setString() 사용한 이유 : bcontent가 문자열이라서
            ps.setInt(2, boardDto.getBno() ); // 2. 작성한 이유 : SQL 문법내 두번째 ? 자리
            // 4. SQL 실행 update = executeUpdate();
            int count = ps.executeUpdate();
            // 5. SQL 결과에 따른 로직/리턴/확인
            if (count == 1){
                return true; // 수정 sql 결과가 1개이면 수정 성공
            } else {
                return false; // 수정 sql 결과가 1이 아니면 수정 실패
            }
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return false; // 예외 발생시 수정 실패
    } // func end

} // class end

