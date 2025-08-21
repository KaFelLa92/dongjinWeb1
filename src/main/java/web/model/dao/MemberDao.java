package web.model.dao;

import org.springframework.stereotype.Repository;
import web.model.dto.MemberDto;

import javax.xml.transform.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    // 4. 내 정보
    // 현재 로그인중(*세션 정보*)인 회원정보(비밀번호 제외)를 모두 조회한다.
    public MemberDto info ( int mno ){
        try{
            String sql = "select * from member where mno = ?"; // rs.next에서 pwd 안넣으면 되므로 와일드카드 사용 가능
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, mno);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                MemberDto memberDto = new MemberDto(); // 패스워드 제외
                memberDto.setMno(rs.getInt("mno"));
                memberDto.setMid(rs.getString("mid"));
                memberDto.setMname(rs.getString("mname"));
                memberDto.setMphone(rs.getString("mphone"));
                memberDto.setMdate(rs.getString("mdate"));
                return memberDto;
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return null; // 로그인 세션(mno) 없을 경우 null
    }

    // 5. 특정 필드/열/컬럼의 값 중복 존재 확인 (중복검사)
    // 중복검사할 DB테이블의 속성명과 값을 매개변수로 받는다
    public boolean check (String type , String data){
        try{
            // ?type=value&data=value 타입을 넣어줘야함 (매개변수)
            // String sql = "select * from member where mid = ?";
            // String sql = "select * from member where mphone = ?";
            // 상기처럼 해도 되지만, 이번에는 타입을 받아서 함
            String sql = "select * from member where " +type+ " = ?"; // where 뒤에 띄어쓰기해야, wheremid 되는 참상 막을 수 있다
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, data);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true; // 중복이면 true
            }
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return false;
    } // func end

    // 6. 회원정보수정
    // 현재 로그인된 회원의 새로운 이름과 연락처를 수정
    //(패스워드는 개별적으로 별도 수정 처리 )
    public boolean update(MemberDto memberDto){
        try{
            String sql = "update member set mname = ? , mphone = ? where mno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, memberDto.getMname());
            ps.setString(2, memberDto.getMphone());
            ps.setInt(3, memberDto.getMno());
            int count = ps.executeUpdate();
            if (count == 1){
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        } // catch end
        return false;
    } // func end

    // 7. "회원비밀번호수정updatePassword()" : 현재 로그인된 회원 패스워드와 일치하면 패스워드 수정
    // 현재 로그인된 회원의 기존 패스워드와 새로운 패스워드를 받아서 기존 패스워드가 일치하면 수정한다.
    // 일회성 객체 이동시 Map 컬렉션 프레임워크로 대체할 수 있음.
    // { "oldpwd" : "pass1234" , "newpwd" : "hong12345" } 이렇게 테스트
    public boolean updatePassword(int mno , Map<String , String> map){
        try{
            String sql = "update member set mpwd = ? where mno = ? and mpwd = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, map.get("newpwd")); // 새 패스워드
            ps.setInt(2, mno);
            ps.setString(3, map.get("oldpwd")); // 기존 패스워드
            int count = ps.executeUpdate();
            return count == 1;
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return false;
    } // func end

    // 8. "회원탈퇴 delete()"	"현재 로그인중인 회원 정보 삭제
    //(실무에서는 *상태변경*으로 관리)"
    public boolean delete(int mno , String oldpwd){
        try{
            String sql = "delete from member where mno = ? and mpwd = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, mno);
            ps.setString(2, oldpwd);
            int count = ps.executeUpdate();
            return count == 1;
        } catch (Exception e) {
            System.out.println(e);
        } // catch end
        return false;
    } // func end

    // [요구사항3] 아래 조건에 따라 아이디찾기 및 비밀번호를 찾기를 기능과 화면 find.jsp 을 구현하시오.
    //1. 아이디 찾기 기능
    //    - 입력: 이름, 연락처
    //    - 처리: 이름+연락처 일치 시 아이디 반환
    //    - 불일치 시 "회원정보 없음" 메시지

    // 9. 아이디찾기 findID
    public MemberDto findId(String mname , String mphone){
        try{
            String sql = "select mid from member where mname = ? and mphone = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mname);
            ps.setString(2, mphone);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                MemberDto memberDto = new MemberDto();
                memberDto.setMid(rs.getString("mid"));
                return memberDto;
            }
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return null;
    }

    //2. 비밀번호 찾기 기능
    //    - 입력: 아이디, 연락처
    //    - 처리: 아이디+연락처 일치 시 새로운 난수(6자릿수) 비밀번호 생성 후 반환
    //    - 생성된 비밀번호를 DB에 업데이트(임시 비밀번호로 사용)

    // 10. 비밀번호 찾기(생성) findPwd
    public String findPwd(MemberDto memberDto){
        try{
            String sql = "update member set mpwd = ? where mid = ? and mphone = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, memberDto.getMpwd());
            ps.setString(2, memberDto.getMid());
            ps.setString(3, memberDto.getMphone());
            int count = ps.executeUpdate();
            if (count == 1){
                return memberDto.getMpwd(); // 현재 비번 반환
            }
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return null;
    } // func end



} // class end
