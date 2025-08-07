package example.실습4.model.dao;

import example.실습4.model.dto.WaitDto;
import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WaitDao {

    // DAO는 SQL 전용으로 쓰기

    // (*) 어노테이션 + 싱글톤
    // 롬복을 통해 해당하는 멤버변수에 Getter 제공
    @Getter
    public static final WaitDao instance = new WaitDao();
    private WaitDao(){connect();}

    // (*) DB 연동
    private String db_url = "jdbc:mysql://localhost:3306/waitingList";
    private String db_user = "root";
    private String db_password = "1234";
    private Connection conn;

    // (*) 드라이버 매니저 연동 함수
    private void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db_url , db_user ,db_password);
        } catch (Exception e){
            System.out.println(e);
        } // catch end
    } // func end

    // 1. 대기 번호 등록 페이지/기능
    //   조건 : 대기번호는 자동부여 되며 연락처 와 인원수만 입력받습니다.
    public boolean waitAdd(WaitDto waitDto){
        try{
            // 1) SQL문 작성
            String sql = "insert into wait (wnumber , wcount) values (? , ?)";
            // 2) DB에 SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3) 매개변수 대입
            ps.setString(1, waitDto.getWnumber());
            ps.setInt(2, waitDto.getWcount());
            // 4) 기재된 SQL 실행
            int count = ps.executeUpdate();
            // 5) 리턴
            if (count == 1) return true;
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return false;
    } // func end

    
    // 2. 대기 현황 전체 조회 페이지/기능
    //   조건 : 대기번호,연락처,인원수,등록일 모든 정보를 전체 출력합니다.
    public List<WaitDto> waitPrint(){
        List<WaitDto> list = new ArrayList<>();
        try{
            // 1. SQL 문 작성
            String sql = "select * from wait";
            // 2. DB에 SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. 매개변수 대입(X)
            // 4. 기재된 SQL 실행
            ResultSet rs = ps.executeQuery();
            // 5. while문 순회하면서 출력
            while (rs.next()){
                int wno = rs.getInt("wno");
                String wnumber = rs.getString("wnumber");
                int wcount = rs.getInt("wcount");
                // 객체 저장
                WaitDto waitDto = new WaitDto(wno , wnumber , wcount);
                list.add(waitDto);
            } // while end
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return list;
    } // func end

}
