package 종합.예제12.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

// 슈퍼 클래스 만들기
public class Dao {
    // [DB 연동]
    private String db_url = "jdbc:mysql://localhost:3306/exam12";
    private String db_user = "root";
    private String db_password = "1234";
    // [DB 연동 멤버변수] * 하위클래스에서 사용할 수 있게 public
    public Connection conn;
    // [DB 연동 생성자] * 싱글톤 아님
    public Dao(){connect();}
    // [DB 연동 메소드]
    private void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db_url, db_user, db_password);
            System.out.println("Dao.connect"); // soutm 확인
        }catch (Exception e){
            System.out.println(e);
        }
    }
} // class end
