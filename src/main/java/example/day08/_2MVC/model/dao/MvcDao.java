package example.day08._2MVC.model.dao;

import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Service로부터 요청 받음 (DB로 연락함)
@Repository // [1] Repository(저장소) 빈 등록
public class MvcDao extends Dao {
    // 다음 레이어(계층) 없어서 @Autowired 없음
    // extends Dao 할 경우 DB연동 상속받아 사용한다.

    public void method(){
        System.out.println("MvcDao.method");
        try{
            String sql = "select * from mvc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while( rs.next() ){
                System.out.println(rs.getString("var1"));
            }
        } catch (Exception e){
            System.out.println(e);
        }
    }

}
