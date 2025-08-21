package web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.PointlogDto;
import web.service.PointlogService;

import java.util.List;

@RestController
@RequestMapping("/pointlog")
public class PointlogController {

    // DI
    private final PointlogService pointlogService;
    @Autowired public PointlogController(PointlogService pointlogService){
        this.pointlogService = pointlogService;
    }

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

    @PostMapping("/add")
    // { "mno" : 1, "plpoint" : 1000, "plcomment" : "회원가입" } 테스트 완
    public int pointAdd(@RequestBody PointlogDto pointlogDto){
        return pointlogService.PointAdd(pointlogDto);
    }


        /*
    [요구사항2] 마이페이지(info.jsp) 에서 포인트 지급 내역 전체 조회
    1. 로그인한 회원(mno)의 모든 포인트 지급 내역을 출력
    2. 지급일시(pldate) 기준 내림차순 정렬
        */
    // http://localhost:8080/pointlog/print
    @GetMapping("/print")
    public List<PointlogDto> pointPrint(HttpSession session){
        // 세션 유효성 검사
        if (session == null || session.getAttribute("loginMno") == null){
            return null;
        }
        // 로그인 상태일 때
        Object obj = session.getAttribute("loginMno");
        // 타입 변환
        int loginMno = (int)obj;
        return pointlogService.pointPrint(loginMno);
    }

        /*
    [요구사항3] 로그인후 현재 포인트(합계) 출력
    1. 로그인 성공 시 header.jsp 상단에 기존 출력된 아이디 옆에 현재 보유 포인트 함께 표시
        예시: hong123 (현재 포인트: 1,200점)
    2. 현재 포인트 계산 방식, pointlog 테이블에서 해당 회원의 모든 지급 포인트 합계를 계산
         */

}
