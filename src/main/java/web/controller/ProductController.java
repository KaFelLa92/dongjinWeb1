package web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.model.dto.ProductDto;
import web.service.FileService;
import web.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired private ProductService productService;
    @Autowired private FileService fileService; // 업로드 시 필요

    // [1] 제품 등록
    @PostMapping("/create")
    public int createProduct(@ModelAttribute ProductDto productDto, HttpServletRequest request){ // @모델어트리뷰트로 가야함. 안쓰면 자동으로 모델어트리뷰트
        // 1. 로그인상태 확인 후, 비 로그인이면 0 반환
        HttpSession session = request.getSession();
        if( session.getAttribute("loginMno") == null) return 0;
        // 2. 제품정보 DB 처리
        int loginMno = (int)session.getAttribute("loginMno");
        productDto.setMno(loginMno); // 현재 로그인된 회원번호를 제품등록 dto에 포함
        int result = productService.createProduct(productDto);
        if (result == 0) {
            return 0;
        }
        // 3. 만약 업로드 파일이 존재하면 업로드 서비스 호출하여 업로드 처리
            // 3-1 제품 등록이 가능하면서, 첨부파일이 비어있지 않고, 첨부파일 목록에서 첫 번째 첨부파일이 비어있지 않으면
        if (result > 0 && !productDto.getUploads().isEmpty() && !productDto.getUploads().get(0).isEmpty()){
            // 3-2 파일 업로드 , List 타입을 반복문 이용하여 여러번 업로드
            for(MultipartFile multipartFile : productDto.getUploads()) {
                // 3-3 업로드 서비스
                String fileName = fileService.fileUpload(multipartFile);
                if(fileName == null) return 0; // 파일명이 null이면 업로드 실패, 0 반환
                // 3-4 업로드 성공시 업로드 된 파일명 DB에 저장
                boolean result2 = productService.createProtuctImage(result , fileName); // result : 제품등록된 pno , fileName ; 업로드된 파일명
                if(result2 == false) return 0; // db에 이미지 저장 실패시 0 반환
            } // for end
        } // if end
        // 4. 처리된 업로드 파일 DB 처리
        return result;
    } // func end


    // [2] 제품 전체 조회
    @GetMapping("/list")
    public List<ProductDto> getAllProduct(HttpSession session){
        return productService.getAllProduct();
    }

    // [3] 제품 상세 조회
    @GetMapping("/find")
    public ProductDto getProduct(@RequestParam int pno){

    }


} // class end
