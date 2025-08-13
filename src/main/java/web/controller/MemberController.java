package web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.MemberDto;
import web.service.MemberService;


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
    @PostMapping ("/login")
    public int login (@RequestBody MemberDto memberDto , HttpServletRequest request){
        // HttpServletRequest : HTTP 요청 정보를 갖는 객체
        // 1. 세션 정보 가져오기
        HttpSession session = request.getSession();
        // 2. 로그인 성공한 회원정보 확인
        int result = memberService.login(memberDto);
        if (result > 0){ // 회원번호가 있으면
            // 3. 세션 정보에 속성 추가하기
            session.setAttribute("loginMno" , result);
        }
        return result;
    }

    // 3. 로그아웃 , 세션은 서버를 재시작하면 초기화됨
    // 로그인 세션을 가진 뒤, http://localhost:8080/member/logout?mno=5 쿼리스트링으로 로그아웃처리
    @GetMapping("/logout")
    public boolean logout (@RequestParam int mno , HttpServletRequest request ){
        // 1. 세션 정보 가져오기
        HttpSession session = request.getSession();
        // * 유효성검사 : 세션이 없거나, 특정 속성값이 없으면 비로그인상태
        if ( session == null || session.getAttribute("loginMno") == null){
            return false;
        }
        // 2. 세션 속성 제거
        session.removeAttribute("loginMno");
        // 3. 반환
        return true;
    }

}
