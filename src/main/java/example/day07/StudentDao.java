package example.day07;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    // DAO에서는 SQL만 한다

    // (*) 싱글톤
    // 롬복 : 해당하는 멤버변수에만 getter 제공받을 수 있다
    @Getter
    // private StudentDao(){}
    public static final StudentDao instance = new StudentDao();
    // public static StudentDao getInstance(){
    //     return instance;
    // }
    private StudentDao(){connect();}

    // (*) DB 연동
    private String db_url = "jdbc:mysql://localhost:3306/spring07";
    private String db_user = "root";
    private String db_password = "1234";
    private Connection conn;

    // 드라이버매니저 연동 함수
    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db_url, db_user, db_password);
        } catch (Exception e) {
            System.out.println(e);
        } // catch end
    } // func end

    // 1. 등록
    public boolean save(StudentDto studentDto){
        try{
            // 1) sql 작성
            String sql = "insert into student (sname, skor, smath) values (? , ? , ? )";
            // 2) 연동된 db(conn)에 sql 기재
            PreparedStatement ps = conn.prepareStatement(sql);
            // 3) 매개변수 대입
            ps.setString(1, studentDto.getSname());
            ps.setInt(2, studentDto.getSkor());
            ps.setInt(3, studentDto.getSmath());
            // 4) 기재된 SQL 실행. 
            int count = ps.executeUpdate();
            // 5) 결과값 반환
            if( count == 1) return true;
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return false;
    } // func end

    // 2. 전체조회
    public List<StudentDto> find(){
        List<StudentDto> list = new ArrayList<>(); // dto 담을 준비. 배열 try-catch 밖에 써주기
        try{
            String sql = "select * from student";
            PreparedStatement ps = conn.prepareStatement(sql);
            // 조회(GET)는 executeQuery(), ResultSet
            ResultSet rs = ps.executeQuery();
            // 로그인 유효성 검사시에는 if(rs.next() {} 로 처리
            while (rs.next()){ // .next() : 다음 레코드로 이동 , while : 끝날때까지(for(;;)로도 대체 가능)
                // rs.get타입( 필드명 또는 번호) : 조회 중인 레코드의 필드값 호출
                int sno = rs.getInt("sno");
                String sname = rs.getString("sname");
                int skor = rs.getInt("skor");
                int smath = rs.getInt("smath");
                String sdate = rs.getString("sdate");
                // 하나씩 객체 저장
                StudentDto studentDto = new StudentDto(sno , sname , skor, smath ,sdate);
                list.add(studentDto);
            } // while end
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return list;
    } // func end
    
}
