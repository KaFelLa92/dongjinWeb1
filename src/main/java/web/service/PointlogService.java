package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.PointlogDao;
import web.model.dto.PointlogDto;

import java.util.List;

@Service
public class PointlogService {


    // DI(의존성 주입)
    private final PointlogDao pointlogDao;
    @Autowired public PointlogService(PointlogDao pointlogDao){
        this.pointlogDao = pointlogDao;
    }

    // [1] 포인트 지급
    public int PointAdd(PointlogDto pointlogDto){
        return pointlogDao.pointAdd(pointlogDto);
    }

    // [2] 내 포인트 지급 내역 전체조회
    public List<PointlogDto> pointPrint(int mno){
        return pointlogDao.pointPrint(mno);
    }


    // [3] 포인트 합계 출력


}
