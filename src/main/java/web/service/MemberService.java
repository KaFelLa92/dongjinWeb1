package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.MemberDao;
import web.model.dto.MemberDto;

import java.util.Map;

@Service
public class MemberService {
    // DI 세팅
    private final MemberDao memberDao;
    @Autowired public MemberService(MemberDao memberDao){
        this.memberDao = memberDao;
    }

    // 1. 회원가입
    public int signup (MemberDto memberDto){
        int result = memberDao.signup(memberDto);
        return result;
    }

    // 2. 로그인
    public int login (MemberDto memberDto){
        int result = memberDao.login(memberDto);
        return result;
    }

    // 4. 서비스
    public MemberDto info(int mno){
        MemberDto result = memberDao.info(mno);
        return result;
    }

    // 5. 중복검사
    public boolean check(String type , String data){
        boolean result = memberDao.check(type, data);
        return result;
    }

    // 6. 회원정보 수정
    // 현재 로그인된 회원의 새로운 이름과 연락처를 수정
    //(패스워드는 개별적으로 별도 수정 처리 )
    public boolean update(MemberDto memberDto){
        boolean result = memberDao.update(memberDto);
        return result;
    }

    // 7. 비밀번호 수정
    public boolean updatePassword(int mno , Map<String, String > map){
        boolean result = memberDao.updatePassword(mno , map);
        return result;
    }

    // 8. 회원탈퇴
    public boolean delete(int mno , String oldpwd){
        boolean result = memberDao.delete(mno, oldpwd);
        return result;
    }

    // 9. 아이디찾기
    public MemberDto findId(String mname , String mphone){
        return memberDao.findId(mname , mphone);
    }

    // 10. 비밀번호찾기
    public String findPwd(MemberDto memberDto){
        return memberDao.findPwd(memberDto);
    }

}
