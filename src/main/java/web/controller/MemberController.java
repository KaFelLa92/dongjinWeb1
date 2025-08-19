package web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.MemberDto;
import web.service.MemberService;

import java.util.Map;


@RestController
@RequestMapping("/member")
public class MemberController {
    // DI 세팅
    private final MemberService memberService;
    @Autowired public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    // 1. 회원가입
    @PostMapping ("/signup")
    public int signup (@RequestBody MemberDto memberDto){
        int result = memberService.signup(memberDto);
        return result;
    }

    // 2. 로그인
    // { "mid" : "hong123" , "mpwd" : "hong12345"}
    @PostMapping ("/login")
    public int login (@RequestBody MemberDto memberDto , HttpServletRequest request){
        // HttpServletRequest : HTTP 요청 정보를 갖는 객체
        // 1. 세션 정보 가져오기
        HttpSession session = request.getSession();
        // 2. 로그인 성공한 회원정보 확인
        int result = memberService.login(memberDto);
        if (result > 0){ // 회원번호가 있으면
            // 3. 세션 정보에 속성 추가하기
            session.setAttribute("loginMno" , result); // result로 속성값 추가할 때, Object로 타입변환됨
        }
        return result;
    }

    // 3. 로그아웃 , 세션은 서버를 재시작하면 초기화됨
    // 로그인 세션을 가진 뒤, http://localhost:8080/member/logout?mno=5 쿼리스트링으로 로그아웃처리
    @GetMapping("/logout")
    public boolean logout (HttpServletRequest request ){
        // 1. 세션 정보 가져오기
        HttpSession session = request.getSession();
        // 2. 유효성검사 : 세션이 없거나, 특정 속성값이 없으면 비로그인상태
        if ( session == null || session.getAttribute("loginMno") == null){
            return false;
        }
        // 3.. 세션 속성 제거
        session.removeAttribute("loginMno");
        // 4. 반환
        return true;
    }

    // 4. 내 정보
    @GetMapping("/info")
    public MemberDto info (HttpServletRequest request){
        // 1. 세션 정보 가져오기
        HttpSession session = request.getSession();
        // 2. 유효성 검사 세션 비어있으면 비로그인 상태
        if ( session == null || session.getAttribute("loginMno") == null){
            return null;
        }
        // 3. 로그인상태이면 세션 정보내 로그인 상태 속성값 호출
        Object obj = session.getAttribute("loginMno");
        // 4. 강제타입변환 (변환할타입명)변수명; 타입변환은 태생이 중요. Object 되기 전에, loginMno는 원래 int였음
        int loginMno = (int)obj;
        // 4. 서비스에 loginMno 전달 후 반환
        MemberDto result = memberService.info(loginMno);
        return result;
    }

    // 5. 특정한 필드/열/컬럼의 중복검사
    // http://localhost:8080/member/check?type=mid&data=hong123 체크 이렇게 하면 됨
    // http://localhost:8080/member/check?type=mname&data=유재석
    // http://localhost:8080/member/check?type=mphone&data=010-1234-5678
    @GetMapping("/check")
    public boolean check (@RequestParam String type , @RequestParam String data){
        boolean result = memberService.check(type, data);
        return result;
    }

    // 6. 회원정보 '수정'
    // 현재 로그인된 회원의 새로운 이름과 연락처를 수정
    //(패스워드는 개별적으로 별도 수정 처리 )
    // { "mname" : "hong123" , "mphone" : "pass1234" }
    @PutMapping("/update")
    public boolean update(@RequestBody MemberDto memberDto , HttpServletRequest request){
        // 1. 세션 정보 가져오기
        HttpSession session = request.getSession();
        // 2. 비로그인 상태 확인 유효성 검사
        if (session == null || session.getAttribute("loginMno") == null){
            return false;
        }
        // 3. 로그인이면 세션 정보내 로그인 상태 속성값 호출 (회원번호 꺼내기)
        Object obj = session.getAttribute("loginMno");
        int loginMno = (int) obj;
        // 4. dto에 담기
        memberDto.setMno(loginMno);
        // 5. 서비스에 반환
        boolean result = memberService.update(memberDto);
        return result;
    } // func end

    // 7. 비밀번호수정
    @PutMapping("/update/password")
    public boolean updatePassword(@RequestBody Map<String,String > map , HttpServletRequest request){
        // 1. 세션 정보
        HttpSession session = request.getSession();
        // 2. 유효성 검사
        if ( session == null || session.getAttribute("loginMno") == null){
            return false;
        }
        // 3. 회원번호 꺼내고 타입 변환
        Object obj = session.getAttribute("loginMno");
        int loginMno = (int)obj;
        // 4. 서비스 반환
        boolean result = memberService.updatePassword(loginMno , map);
        // 세션 만료 후 로그아웃
        session.removeAttribute("loginMno");
        return result;
    }

    // 8. 회원삭제
    // http://localhost:8080/member/delete?oldpwd=hong12345 으로 쿼리스트링 처리.
    @DeleteMapping("/delete")
    public boolean delete(@RequestParam String oldpwd , HttpSession session){
        // 1. 세션 정보 (매개변수로 HttpSession 넣어서 생략)
        // 2. 유효성 검사(비로그인 상태)
        if (session == null || session.getAttribute("loginMno") == null){
            return false;
        }
        // 3. 회원번호 꺼내고 타입 변환 (한 수식으로 해결)
        int loginMno = (int)session.getAttribute("loginMno");
        // 4. 서비스 반환
        boolean result = memberService.delete(loginMno , oldpwd);
        // 삭제되었으면(세션만료) 로그아웃
        if(result) {
            session.removeAttribute("loginMno");
        }
        return result;
    }

    // 9. 아이디찾기
    // http://localhost:8080/member/find?mname=유재석&mphone=010-1234-1234 이렇게 검사
    @GetMapping("/find")
    public MemberDto findId(String mname, String mphone){
        return memberService.findId(mname , mphone);
    }

    // 10. 비밀번호 찾기(생성)
    @PutMapping("/find")
    public String findPwd(@RequestBody MemberDto memberDto){
        return memberService.findPwd(memberDto);
    }

}
