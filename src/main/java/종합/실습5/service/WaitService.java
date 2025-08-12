package 종합.실습5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import 종합.실습5.model.dao.WaitDao;
import 종합.실습5.model.dto.WaitDto;

import java.util.List;

@Service
public class WaitService {
    // DI
    private final WaitDao waitDao;
    @Autowired public WaitService(WaitDao waitDao){
        this.waitDao = waitDao;
    }

    // [1] 등록
    public boolean waitWrite(WaitDto waitDto){
        System.out.println("WaitService.waitAdd");
        System.out.println("waitDto = " + waitDto);
        boolean result = waitDao.waitWrite(waitDto);
        return result;
    } // func end

    // [2] 전체조회
    public List<WaitDto> waitList(){
        System.out.println("WaitService.waitList");
        System.out.println("waitDao = " + waitDao);
        List<WaitDto> result = waitDao.waitList();
        return result;
    }

    // [3] 특정조회
    public WaitDto waitView(int wno){
        System.out.println("WaitService.waitView");
        System.out.println("waitDao = " + waitDao);
        WaitDto result = waitDao.waitView(wno);
        return result;
    }

    // [4] 삭제
    public boolean waitDelete(int wno){
        System.out.println("WaitService.waitDelete");
        System.out.println("wno = " + wno);
        boolean result = waitDao.waitDelete(wno);
        return result;
    }

    // [5] 수정
    public boolean waitUpdate(WaitDto waitDto){
        System.out.println("WaitService.waitUpdate");
        System.out.println("waitDto = " + waitDto);
        boolean result = waitDao.waitUpdate(waitDto);
        return result;
    }

}
