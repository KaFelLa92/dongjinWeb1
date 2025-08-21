package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.MemberDao;
import web.model.dto.MemberDto;
import web.model.dto.PointlogDto;

import java.util.Map;

@Service
public class MemberService {
    // DI 세팅
    private final MemberDao memberDao;
    @Autowired public MemberService(MemberDao memberDao){
        this.memberDao = memberDao;
    }
    @Autowired PointlogService pointlogService;


    // 1. 회원가입
    public int signup (MemberDto memberDto){
        int result = memberDao.signup(memberDto);
        if( result > 0){
            int plpoint = 1000;
            String plcomment = "회원가입";
            PointlogDto pointlogDto = new PointlogDto();
            pointlogDto.setMno(result);
            pointlogDto.setPlpoint(plpoint);
            pointlogDto.setPlcomment(plcomment);
            pointlogService.PointAdd(pointlogDto);
        }
        return result;
    }

    // 2. 로그인
    public int login (MemberDto memberDto){
        int result = memberDao.login(memberDto);
        if (result > 0){
            PointlogDto pointlogDto = new PointlogDto();
            pointlogDto.setMno(result);
            pointlogDto.setPlpoint(10);
            pointlogDto.setPlcomment("로그인");
            pointlogService.PointAdd(pointlogDto);
        }
        return result;
    }

    // 4. 내 정보
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
        int randomPwd = (int) (Math.random() * 1000000);
        String newPwd = String.format("%06d" , randomPwd); // 6자리 난수
        memberDto.setMpwd(newPwd); // 반환한 비번에 6자리 난수를 대입
        return memberDao.findPwd(memberDto);
    }

}
