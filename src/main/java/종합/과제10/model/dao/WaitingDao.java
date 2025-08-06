package 종합.과제10.model.dao;

import 종합.과제10.model.dto.WaitingDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class WaitingDao {
    // (*) 싱글톤
    private WaitingDao() {
        connect(); // 커넥트 함수 생성자에 넣어서 초기화할 때 연동되게 하기
    }
    public static final WaitingDao instance = new WaitingDao();
    public static WaitingDao getInstance(){
        return instance;
    }

    //(*) DB 연동
    // 1. 연결할 곳 정보 쓰기
    private String db_url = "jdbc:mysql://localhost:3306/waitingList";
    private String db_user = "root";
    private String db_password = "1234";
    private Connection conn;

    // 2. 연동 함수
    private void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db_url , db_user , db_password);
        } catch (Exception e){
            System.out.println(e);
        }
    } // func end



    // (1) 대기등록 기능 구현
    // 대기번호 자동부여(PK), number와 count 입력 받기
    public boolean WaitAdd(WaitingDto waitingDto){

        try{
            // 1. SQL 작성
            String sql = "insert into wait (wnumber , wcount) values (? , ?); ";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 대입
            ps.setString(1 , waitingDto.getWnumber()); // 첫 번째 등록하는 매개변수 dto의 넘버 값 대입 (스트링)
            ps.setInt(2 , waitingDto.getWcount()); // 두 번째 등록하는 매개변수 dto의 count 값 대입 (인트)
            // 4. SQL 실행 : select만 executeQuery(), 나머지는 executeUpdate()
            int count = ps.executeUpdate();
            // 5. SQL 결과에 따른 로직/리턴/확인
            if ( count >= 1 ){
                return true; // 1개 이상 insert하면 성공
            } else {
                return false; // 1개 미만 insert면 실패
            }
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return false; // catch문에서 막히면 실패
    } // func end



    // (2) 대기조회 기능 구현
    // wno, wnumber, wcount 순으로 출력
    public ArrayList<WaitingDto> waitPrint(){
        // try-catch 구문 밖에 어레이리스트 변수 생성해서, 변수를 메소드 외부로 리턴하기.
        ArrayList<WaitingDto> list = new ArrayList<>();
        try{
            // 1. SQL 작성
            String sql = "select * from wait";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 대입(없으면 생략)
            // 4. SQL 실행 , select는 executeQuery() , 조회는 리절트셋 타입
            ResultSet rs = ps.executeQuery();
            // 5. SQL 결과에 따른 로직/리턴/확인
            while (rs.next()){ // rs.next() : 남은 거 없을 때까지 계속 while 시킨다.
                int wno = rs.getInt("wno");
                String wnumber = rs.getString("wnumber");
                int wcount = rs.getInt("wcount");
                WaitingDto waitingDto = new WaitingDto(wno , wnumber , wcount); // 레코드 1개 완성.
                // 배열리스트 타입 리스트 변수에 담기
                list.add(waitingDto);
            } // while end
        }catch (Exception e){
            System.out.println(e);
        }
        return list; // 만들어진 리스트 배열을 컨트롤러로 보낸다.
    } // func end



    // (3) 대기삭제 기능 구현
    // wno 받아서 삭제
    public boolean waitDelete( int wno ){
        // int wno는 매개변수이자 삭제할 게시물의 프라이멀 키(식별 번호)
        try{
            // 1. SQL 작성
            String sql = "delete from wait where wno = ?"; // wait 테이블의 wno를 (scan으로 찍은 숫자와 일치하면) 삭제할거다
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 대입
            ps.setInt(1, wno); // SQL 문법내 첫번째 ?의 인트 타입으로 wno 값 대입
            // 4. SQL 실행 executeUpdate();
            int count = ps.executeUpdate();
            // 5. SQL 결과에 따른 로직/리턴/확인
            if ( count == 1){
                return true; // SQL 결과 1이면 삭제
            } else {
                return false; // 1 아니면 삭제 실패
            }

        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return false;
    } // func end



    // (4) 대기수정 기능 구현
    // count만 수정 가능
    public boolean waitUpdate( WaitingDto waitingDto){
        try {
            // 1. SQL 작성
            String sql = "update wait set wcount = ? where wno = ?";
            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3. SQL 매개변수 대입 , SQL 문법내 ? 개수만큼
            ps.setInt(1 , waitingDto.getWcount());  // setInt는 wcount가 int라서 사용함. 첫 번째 나오는 ?
            ps.setInt(2 , waitingDto.getWno());     // 두 번째 ?이므로 파라미터인덱스 2.
            // 4. SQL 실행 executeUpdate()
            int count = ps.executeUpdate();
            // 5. SQL 결과 로직/리턴/확인
            if ( count == 1){
                return true; // 결과 1이면 수정 성공
            } else {
                return false; // 1 아니면 수정 실패
            }

        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return false;
    } // func end






} // class end