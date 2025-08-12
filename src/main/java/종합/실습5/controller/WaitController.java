package 종합.실습5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import 종합.실습5.model.dto.WaitDto;
import 종합.실습5.service.WaitService;

import java.util.List;

@RestController
@RequestMapping ("/waiting")
public class WaitController {
        /*
    0. /waiting/index.jsp 와 /waiting/header.jsp 사용 합니다.

     */
    // 생성자에 주입
    private final WaitService waitService;
    @Autowired public WaitController(WaitService waitService){
        this.waitService = waitService;
    }

    /*
    1. 대기 현황 등록 기능 , /waiting/write.jsp
        조건 : 대기번호는 자동부여 되며 연락처 와 인원수만 입력받아 저장합니다.
    */
    @PostMapping ("/write")
    public boolean waitWrite(@RequestBody WaitDto waitDto){
        System.out.println("WaitController.waitAdd");
        System.out.println("waitDto = " + waitDto);

        boolean result = waitService.waitWrite(waitDto);
        return result;

    } // func end

    /*
    2. 대기 현황 전체 조회 기능 , /waiting/list.jsp
   조건 : 모든 대기 현황의 대기번호, 연락처, 인원수, 등록 일시를 출력합니다.
     */

    @GetMapping ("/list")
    public List<WaitDto> waitList(){
        System.out.println("WaitController.waitList");
        System.out.println("waitService = " + waitService);

        List<WaitDto> result = waitService.waitList();
        return result;
    }

    /*
    3. 특정 대기 현황 조회 기능 ,  /waiting/view.jsp
   조건 : 선택한 대기번호의 대기번호, 연락처, 인원수, 등록 일시를 출력합니다.
     */

    // http://localhost:8080/waiting/view?wno=1 식으로 조회
    @GetMapping ("/view")
    public WaitDto waitView(@RequestParam int wno){
        System.out.println("WaitController.waitView");
        System.out.println("wno = " + wno);
        WaitDto result = waitService.waitView(wno);
        return result;
    }

    /*
    4. 특정 대기 현황 삭제 기능  , /waiting/view.jsp포함됩니다.
   조건 : 선택한 대기번호의 정보를 삭제합니다.
     */

    // http://localhost:8080/waiting/view?wno=1 이렇게 삭제. 한 번 더 하니까 이미 삭제되어 false
    @DeleteMapping ("/view")
    public boolean waitDelete(@RequestParam int wno){
        System.out.println("WaitController.waitDelete");
        System.out.println("wno = " + wno);
        boolean result = waitService.waitDelete(wno);
        return result;
    }

    /*
    5. 특정 대기 현황 수정 기능 , /waiting/update.jsp
   조건1 : 선택한 대기번호의 연락처 와 인원수 를 출력합니다.
   조건2 : 새로운 연락처 와 인원수 입력받아 선택한 대기번호 정보를 수정합니다.
     */

    @PutMapping ("/update")
    public boolean waitUpdate(@RequestBody WaitDto waitDto){
        System.out.println("WaitController.waitUpdate");
        System.out.println("waitDto = " + waitDto);
        boolean result = waitService.waitUpdate(waitDto);
        return result;
    }

}
