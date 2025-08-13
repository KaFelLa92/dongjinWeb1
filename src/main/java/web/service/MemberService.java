package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.MemberDao;
import web.model.dto.MemberDto;

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


}
