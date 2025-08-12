package 종합.과정평가.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import 종합.과정평가.model.dao.MemberDao;
import 종합.과정평가.model.dto.MemberDto;
import 종합.과정평가.model.dto.MemberSaleDto;

import java.util.List;

@Service
public class MemberService {
    // DI
    private final MemberDao memberDao;
    @Autowired public MemberService(MemberDao memberDao){
        this.memberDao = memberDao;
    }
    
    // 1. 회원 등록
    public boolean memberAdd(MemberDto memberDto){
        boolean result = memberDao.memberAdd(memberDto);
        return result;
    }

    // 2. 회원 조회
    public List<MemberDto> memberList(){
        List<MemberDto> result = memberDao.memberList();
        return result;
    }

    // 3. 회원 매출조회
    public List<MemberSaleDto> memberSales(){
        List<MemberSaleDto> result = memberDao.memberSales();
        return result;
    }

    // 4. 회원 정보수정
    public boolean memberUpdate(MemberDto memberDto){
        boolean result = memberDao.memberUpdate(memberDto);
        return result;
    }


}
