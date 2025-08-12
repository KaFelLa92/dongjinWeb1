package 종합.과정평가.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import 종합.과정평가.model.dto.MemberDto;
import 종합.과정평가.model.dto.MemberSaleDto;
import 종합.과정평가.service.MemberService;

import java.util.List;

@RestController
@RequestMapping ("/assessment")
public class MemberController {
    // DI
    private final MemberService memberService;
    @Autowired public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    // 1. 회원 등록
    //    홈쇼핑 회원 등록 페이지에서 처리 (add)
    @PostMapping ("/add")
    public boolean memberAdd(@RequestBody MemberDto memberDto){
        boolean result = memberService.memberAdd(memberDto);
        return result;
    }

    // 2. 회원 조회
    // 회원목록조회/수정 페이지에서 진행(list)
    // MemberDto 순회해서 테이블에 출력한다
    // 회원번호, 회원성명, 전화번호, 주소, 가입일자, 고객등급, 거주지역 순으로 출력한다.
    // 고객등급 A는 VIP, B는 일반, C는 직원으로 출력한다.
    @GetMapping ("/list")
    public List<MemberDto> memberList(){
        List<MemberDto> result = memberService.memberList();
        return result;
    }

    // 3. 회원매출조회
    // 회원매출조회 페이지에서 진행(sale)
    // 회원번호, 회원성명, 고객등급, 매출 순으로 출력. 매출식은 같은 회원 번호 기준으로 sum(price)
    // 매출이 높은 순서대로 조회 (desc)
    // 매출 0이면 조회하지 않음
    @GetMapping ("/sale")
    public List<MemberSaleDto> memberSales(){
        List<MemberSaleDto> result = memberService.memberSales();
        return result;
    }


    // 4. 회원정보수정
    // 회원목록조회/수정에서 회원번호 클릭하면 수정 페이지 등장 (update)
    // 해당 회원정보 수정 후 '회원정보수정이 완료되었습니다' 메시지 출력
    @PutMapping ("/update")
    public boolean memberUpdate(@RequestBody MemberDto memberDto){
        boolean result = memberService.memberUpdate(memberDto);
        return result;
    }

    // * 회원넘버입력식
    @GetMapping ("/add")
    public int memberAddNextNo(){
        int result = memberService.memberAddNextNo();
        return result;
    }


}
