package web.model.dao;

import org.springframework.stereotype.Repository;
import web.model.dto.MemberDto;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
public class MemberDao extends Dao {

    // 1. 회원가입 signup    
    //    (private int mno; 는 자동 번호 배차됨)
    //    private String mid;
    //    private String mpwd;
    //    private String mname;
    //    private String mphone;
    //    (private String mdate; 는 자동 날짜 배차됨)
    // 반환타입 int : 0은 회원가입 실패 , 1이상이면 성공한 회원번호 반환

    public int signup (MemberDto memberDto){
        try{
            String sql = "insert into member(mid, mpwd, mname, mphone) values(? , ? , ? , ?)";
            // SQL에 auto_increment 자동 PK값 결과 반환
            PreparedStatement ps = conn.prepareStatement(sql , PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, memberDto.getMid());
            ps.setString(2, memberDto.getMpwd());
            ps.setString(3, memberDto.getMname());
            ps.setString(4, memberDto.getMphone());
            int count = ps.executeUpdate();
            if (count == 1) {
                // auto_increment로 자동 할당된 pk값 반환하여 rs로 조작
                ResultSet rs = ps.getGeneratedKeys();
                // 자동 할당된 pk값 중에 첫번째 pk값으로 이동
                if (rs.next()){
                    int mno = rs.getInt(1); // pk값 가져오기
                    return mno;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        } // catch end
        return 0;
    } // func end

    // 2. 로그인
    // 아이디와 비밀번호를 입력받아 일치하면 회원번호를 "loginMno" 속성명으로 *세션*에 저장
    public int login(MemberDto memberDto){
        try{
            // dao sql에서 조회를 하고, 일치하면 controller에서 int값(loginMno)를 반환한다.
            String sql = "select * from member where mid = ? and mpwd = ?";
            // 현재 PK값 반환
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, memberDto.getMid());
            ps.setString(2, memberDto.getMpwd());
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int mno = rs.getInt("mno");
                return mno; // 로그인 성공시 조회한 회원번호 반환
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return 0;
    } // func end
    
}
