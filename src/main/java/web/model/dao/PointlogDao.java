package web.model.dao;

import org.springframework.stereotype.Repository;
import web.model.dto.PointlogDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PointlogDao extends Dao {

        /*
    [요구사항1] 포인트 지급 조건
    1. 회원가입 완료 시 → +1000 포인트 지급 기록 생성
    2. 로그인 성공 시마다 → +10 포인트 지급 기록 생성
    3. 지급 이력은 pointlog 테이블에 저장
        plno (AUTO_INCREMENT, PK)
        mno (회원번호, FK)
        plpoint (지급 포인트, INT)
        plcomment (사유: '회원가입', '로그인')
        pldate (지급일시, DATETIME DEFAULT NOW())
        */

        public int pointAdd(PointlogDto pointlogDto){ // 반환할 값 : plpoint의 정수값
        try{
            String sql = "insert into pointlog (mno, plpoint , plcomment) values (? , ? , ?)";
            PreparedStatement ps = conn.prepareStatement(sql); // 
            ps.setInt(1, pointlogDto.getMno());
            ps.setInt(2, pointlogDto.getPlpoint());
            ps.setString(3, pointlogDto.getPlcomment());
            if( ps.executeUpdate() == 1);
            return pointlogDto.getPlpoint();
        }catch (Exception e){
            System.out.println(e);
        } // catch end
        return 0;
    } // func end

        /*
    [요구사항2] 마이페이지(info.jsp) 에서 포인트 지급 내역 전체 조회
    1. 로그인한 회원(mno)의 모든 포인트 지급 내역을 출력
    2. 지급일시(pldate) 기준 내림차순 정렬
        */

    public List<PointlogDto> pointPrint(int mno){
        List<PointlogDto> list = new ArrayList<>();
        try{
            String sql = "select * from pointlog where mno = ? order by pldate desc;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, mno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                PointlogDto pointlogDto = new PointlogDto();
                pointlogDto.setPlno(rs.getInt("plno"));
                pointlogDto.setMno(rs.getInt("mno"));
                pointlogDto.setPlpoint(rs.getInt("plpoint"));
                pointlogDto.setPlcomment(rs.getString("plcomment"));
                pointlogDto.setPldate(rs.getString("pldate"));
                list.add(pointlogDto);
            }
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return list;
    }

        /*
    [요구사항3] 로그인후 현재 포인트(합계) 출력
    1. 로그인 성공 시 header.jsp 상단에 기존 출력된 아이디 옆에 현재 보유 포인트 함께 표시
        예시: hong123 (현재 포인트: 1,200점)
    2. 현재 포인트 계산 방식, pointlog 테이블에서 해당 회원의 모든 지급 포인트 합계를 계산
         */

    public int pointSum(int mno){
        try{
            String sql = "select sum(plpoint) from pointlog where mno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, mno);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                PointlogDto pointlogDto = new PointlogDto();
                int count = pointlogDto.getPlpoint();
                return count;
            }
        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return 0;
    } // func end



}
