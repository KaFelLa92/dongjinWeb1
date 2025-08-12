package 종합.과정평가.model.dao;

import org.springframework.stereotype.Repository;
import 종합.과정평가.model.dto.MemberDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MemberDao extends Dao {

    // 1. 회원 등록
    //    홈쇼핑 회원 등록 페이지에서 처리 (add)
    //    private int custno;
    //    private String custname;
    //    private String phone;
    //    private String address;
    //    private String joindate;
    //    private String grade;
    //    private String city;
    // 등록하면 member_tbl_02에 올라감
    // 회원번호(자동발생), 가입일자는 회원 등록 인풋에 미리 생성되어 있다.
    // 유효성 검사 : 값 입력되어있지 않을 때 등록 클릭하면 '회원성명 입력되지 않았습니다' 출력 (view탭에서 alert()로 )
    // 각 항목별로 해야함
    // 유효성 검사 통과하면 '회원등록이 완료 되었습니다!' 알러트 뜨고, 테이블에 저장함.

    public boolean memberAdd(MemberDto memberDto) {
        try{
            String sql = "insert into member_tbl_02(custname, phone, address, grade, city) values(? , ? , ? , ? , ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, memberDto.getCustname());
            ps.setString(2, memberDto.getPhone());
            ps.setString(3, memberDto.getAddress());
            ps.setString(4, memberDto.getGrade());
            ps.setString(5, memberDto.getCity());
            int count = ps.executeUpdate();
            if( count >= 1 ){
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    } // func end

    // 2. 회원 조회
    // 회원목록조회/수정 페이지에서 진행(list)
    // MemberDto 순회해서 테이블에 출력한다
    // 회원번호, 회원성명, 전화번호, 주소, 가입일자, 고객등급, 거주지역 순으로 출력한다.
    // 고객등급 A는 VIP, B는 일반, C는 직원으로 출력한다.
    public List<MemberDto> memberList (){
        List<MemberDto> result = new ArrayList<>();
        try{
            String sql = "select * from member_tbl_02";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                MemberDto memberDto = new MemberDto();
                memberDto.setCustno(rs.getInt("custno"));
                memberDto.setCustname(rs.getNString("custname"));
                memberDto.setPhone(rs.getNString("phone"));
                memberDto.setAddress(rs.getNString("address"));
                memberDto.setJoindate(rs.getNString("joindate"));
                memberDto.setGrade(rs.getNString("grade"));
                memberDto.setCity(rs.getNString("city"));
                result.add(memberDto);
            }
            rs.close(); ps.close();
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return result;
    } // func end

    // 3. 회원매출조회
    // 회원매출조회 페이지에서 진행(money)
    // 회원번호, 회원성명, 고객등급, 매출 순으로 출력. 매출식은 같은 회원 번호 기준으로 sum(price)
    // 매출이 높은 순서대로 조회 (desc)
    // 매출 0이면 조회하지 않음

    // 4. 회원정보수정
    // 회원목록조회/수정에서 회원번호 클릭하면 수정 페이지 등장 (update)
    // 해당 회원정보 수정 후 '회원정보수정이 완료되었습니다' 메시지 출력


}
