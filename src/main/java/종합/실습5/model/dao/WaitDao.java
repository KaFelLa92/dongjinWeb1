package 종합.실습5.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import 종합.실습5.model.dto.WaitDto;
import 종합.예제11.model.dto.BoardDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WaitDao extends Dao {

    /*
        1. 대기 현황 등록 기능 , /waiting/write.jsp
        조건 : 대기번호는 자동부여 되며 연락처 와 인원수만 입력받아 저장합니다.

        2. 대기 현황 전체 조회 기능 , /waiting/list.jsp
       조건 : 모든 대기 현황의 대기번호, 연락처, 인원수, 등록 일시를 출력합니다.

        3. 특정 대기 현황 조회 기능 ,  /waiting/view.jsp
           조건 : 선택한 대기번호의 대기번호, 연락처, 인원수, 등록 일시를 출력합니다.

        4. 특정 대기 현황 삭제 기능  , /waiting/view.jsp포함됩니다.
           조건 : 선택한 대기번호의 정보를 삭제합니다.

        5. 특정 대기 현황 수정 기능 , /waiting/update.jsp
           조건1 : 선택한 대기번호의 연락처 와 인원수 를 출력합니다. -> 출력 단위는 jsp에서 처리
           조건2 : 새로운 연락처 와 인원수 입력받아 선택한 대기번호 정보를 수정합니다. -> 수정 단위를 java에서 처리
     */

    // 1) 대기 현황 등록
    public boolean waitWrite(WaitDto waitDto){
        try{
            String sql = "insert into wait(wnumber , wcount) values(? , ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, waitDto.getWnumber());
            ps.setInt(2, waitDto.getWcount());
            int count = ps.executeUpdate();
            if ( count == 1){
                return true;
            } else {
                return false;
            }
        } catch (Exception e){
            System.out.println();
        } // catch end
        return false;
    } // func end

    // 2) 대기 전체 조회
    public List<WaitDto> waitList(){
        List<WaitDto> result = new ArrayList<>();
        try{
            String sql = "select * from wait";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                WaitDto waitDto = new WaitDto();
                waitDto.setWno(rs.getInt("wno"));
                waitDto.setWnumber(rs.getString("wnumber"));
                waitDto.setWcount(rs.getInt("wcount"));
                waitDto.setWdate(rs.getString("wdate"));
                result.add(waitDto);
            }
            rs.close();
            ps.close();
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return result;
    } // func end

    // 3) 특정 대기 조회
    public WaitDto waitView(int wno){
        WaitDto result = new WaitDto();
        try{
            String sql = "select * from wait where wno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, wno);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                String wnumber = rs.getString("wnumber");
                int wcount = rs.getInt("wcount");
                String wdate = rs.getString("wdate");
                result = new WaitDto(wno, wnumber, wcount, wdate);
            }
            rs.close();
            ps.close();
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return result;
    }

    // 4) 대기 삭제
    public boolean waitDelete(int wno){
        try{
            String sql = "delete from wait where wno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, wno);
            int count = ps.executeUpdate();
            if(count == 1){
                return true;
            } return false;
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return false;
    } // func end

    // 5) 대기 수정
    public boolean waitUpdate(WaitDto waitDto){
        try{
            String sql = "update wait set wnumber = ? , wcount = ? where wno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, waitDto.getWnumber());
            ps.setInt(2, waitDto.getWcount());
            ps.setInt(3, waitDto.getWno());
            int count = ps.executeUpdate();
            if( count == 1){
                return true;
            } return false;
        } catch (Exception e){
            System.out.println(e);
        }
        return false;
    }


}
