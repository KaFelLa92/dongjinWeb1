package 종합.예제12.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import 종합.예제12.model.dto.BoardDto;
import 종합.예제12.service.BoardService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BoardDao extends Dao{

    // 1. 게시물 등록
    public boolean boardWrite(BoardDto boardDto){
        try{
            String sql = "insert into board (bcontent , bwriter) values (? , ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, boardDto.getBcontent());
            ps.setString(2, boardDto.getBwriter());
            int count = ps.executeUpdate();
            if( count == 1) {
                return true;
            }
            return false;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    } // func end

    // 2. 전체 조회
    public List<BoardDto> boardPrint(){
        List<BoardDto> list = new ArrayList<>();
        try{
            String sql = "select * from board";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int bno = rs.getInt("bno");
                String bcontent = rs.getString("bcontent");
                String bwriter = rs.getString("bwriter");
                BoardDto boardDto = new BoardDto(bno , bcontent , bwriter);
                list.add(boardDto);
            }

        } catch (Exception e){
            System.out.println(e);
        }
        return list;
    }

    // 3. 특정 조회
    public BoardDto boardFind(int bno){
        BoardDto dto = new BoardDto();
        try{

        } catch (Exception e){
            System.out.println(e);
        } // catch end
        return dto;
    } // func end

    // 4. 게시물 삭제

    // 5. 게시물 수정

} // class end
