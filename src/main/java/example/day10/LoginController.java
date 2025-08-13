package example.day10;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

@RestController             // HTTP 통신 컨트롤러
@RequestMapping("/day10")   // 공통 URL
public class LoginController {

    // 1. 로그인 : 아이디와 패스워드가 일치하면 *세션*에 로그인 성공한 회원번호 저장
    // ex] console 프로젝트에서는 : static int loginMno
    @PostMapping("/login") // 패스워드 중요 데이터가 있어, queryString보다 body를 사용한다
    public boolean login(@RequestBody LoginDto loginDto , HttpServletRequest request){
        // HttpServletRequest : HTTP 요청 정보를 갖는 객체
        System.out.println( request.getRemoteAddr());   // 통신 요청한 클라이언트(사용자)의 IP 확인
        System.out.println( request.getRemotePort());   // 통신 요청한 클라이언트의 PORT 확인
        System.out.println( request.getHeader("User-Agent")); // 통신 요청한 클라이언트의 브라우저정보 확인, HTTP Header
        // ** 세션 정보 가져오기 , 세션이란? 톰캣 서버내 저장소
        HttpSession session = request.getSession();
        System.out.println(session.getId());            // 세션의 식별 번호 : 브라우저마다 , pc마다 다르게 할당 // 사용자 마다 개인 저장소
        System.out.println(session.getCreationTime());  // 세션의 생성시간(ms) : 최초 요청한 시간
        System.out.println(session.getLastAccessedTime()); // 세션의 마지막 접근 시간(ms) , ms(밀리초)
        System.out.println(session.getMaxInactiveInterval()); // 세션의 최대 유효 시간(초)
        // ** 세션 속성 추가하기 , 속성이란? 키와 값으로 구성된 메모리 공간
        session.setAttribute("loginMno" , 3); // .setAttribute("속성명" , 속성값);
        // loginMno라는 속성명으로 3이라는 데이터 저장하겠다는 뜻
        return true;
    } // func end

    // 2. 로그인된 정보 확인 : 현재 세션에 저장된 회원정보 확인
    @GetMapping("/info")
    public boolean info(HttpServletRequest request){
        // 1. 요청 서블릿에서 세션정보 꺼내기
        HttpSession session = request.getSession();
        // 2. 세션정보내 원하는 속성명 값 꺼내기 , 개발자 의도와 상관없이 값은 무조건 Object 타입
        Object obj = session.getAttribute("loginMno");
            if (obj == null) {
                System.out.println("[비로그인 상태]");
                return false;
            }
        // 3. 타입변환 필요
        int loginMno = (int)obj;
        System.out.println(loginMno);
        return true;
    } // func end

    // 3. 로그아웃 : 세션정보내 속성 제거하기
    @GetMapping("/logout")
    public boolean logout( HttpServletRequest request ){
        // 1. 세션 정보 가져오기
        HttpSession session = request.getSession();
        // 2-1. 특정한 세션 제거하기 (둘 중에 하나 쓰기 1)
        session.removeAttribute("loginMno");
        // 2-2. 전체 속성 제거하기 (둘 중에 하나 쓰기 2)
        // session.invalidate();
        return true;
    }

} // class end

