package 종합.과정평가.model.dao;

import org.springframework.stereotype.Repository;
import 종합.과정평가.model.dto.MemberDto;
import 종합.과정평가.model.dto.MemberSaleDto;

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
                memberDto.setCustname(rs.getString("custname"));
                memberDto.setPhone(rs.getString("phone"));
                memberDto.setAddress(rs.getString("address"));
                memberDto.setJoindate(rs.getString("joindate"));
                memberDto.setGrade(rs.getString("grade"));
                memberDto.setCity(rs.getString("city"));
                result.add(memberDto);
            }
            rs.close(); ps.close();
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return result;
    } // func end

    // 3. 회원매출조회
    // 회원매출조회 페이지에서 진행(sale)
    // 회원번호, 회원성명, 고객등급, 매출 순으로 출력. 매출식은 같은 회원 번호 기준으로 sum(price)
    // 매출이 높은 순서대로 조회 (desc)
    // 매출 0이면 조회하지 않음
    public List<MemberSaleDto> memberSales(){
        List<MemberSaleDto> result = new ArrayList<>();
        try{
            String sql = "select m.custno , m.custname , m.grade , sum(mo.price) as 매출\n" +
                    "from member_tbl_02 m inner join money_tbl_02 mo on m.custno = mo.custno\n" +
                    "group by m.custno, m.custname, m.grade\n" +
                    "having sum(mo.price) > 0\n" +
                    "order by 매출 desc;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                MemberSaleDto memberSaleDto = new MemberSaleDto();
                memberSaleDto.setCustno(rs.getInt("custno"));
                memberSaleDto.setCustname(rs.getString("custname"));
                memberSaleDto.setGrade(rs.getString("grade"));
                memberSaleDto.setSale(rs.getInt("매출")); // SQL문에서 as 쓰면 여기도 바꿔줘야함
                result.add(memberSaleDto);
            }
        rs.close(); ps.close();
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return result;
    }

    // 4. 회원정보수정
    // 회원목록조회/수정에서 회원번호 클릭하면 수정 페이지 등장 (update)
    // 해당 회원정보 수정 후 '회원정보수정이 완료되었습니다' 메시지 출력
    // { "custno" : "100001" , "custname" : "새사냥" , "phone" : "011-3344-5678" , "address" : "아이쿠" , "grade" : "A" , "city" : "01" } 통신 이렇게
    public boolean memberUpdate(MemberDto memberDto){
        try{
            String sql = "update member_tbl_02 set custname = ? , phone = ? , address = ? , grade = ? , city = ? where custno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, memberDto.getCustname());
            ps.setString(2, memberDto.getPhone());
            ps.setString(3, memberDto.getAddress());
            ps.setString(4, memberDto.getGrade());
            ps.setString(5, memberDto.getCity());
            ps.setInt(6, memberDto.getCustno());
            int count = ps.executeUpdate();
            if (count == 1){
                return true;
            } return false;
        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    } // func end


    // * 회원가입용으로 현재 custno에서 +1하는 코드 만들기
    public int memberAddNextNo(int custno){
        int nextNo = 0;

        try{
            String sql = ""

        } catch (Exception e){
            System.out.println(e);
        }
    }

}
