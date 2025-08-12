package 종합.실습5.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dao {

    // [ DB 연동 ]
    private String db_url = "jdbc:mysql://localhost:3306/task5";
    private String db_user = "root";
    private String db_password = "1234";
    // [ DB 연동 멤버변수 ]
    public Connection conn;
    // [ DB 연동 생성자 ]
    public Dao( ){ connect();};
    // [ DB 연동 메소드 ]
    private void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db_url, db_user, db_password);
            System.out.println("Dao.connect");
        } catch (Exception e){
            System.out.println(e);
        }
    } // func end

} // class end
