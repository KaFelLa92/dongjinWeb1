package web.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.service.FileService;

@RestController // 스프링 컨테이너의 빈 등록
@RequestMapping ("/file")
public class FileController {
    @Autowired private FileService fileService; // 빈 꺼내오기 (DI)
    // [1] 업로드
    // POST, http://localhost:8080/file/upload
    // [Headers] Content-Type : multipart/form-data
    // [BODY] 우측셀렉트 Form, 텍스트 multipartFile 셀렉트 File
    // 업로드
    @PostMapping("/upload")
    public String fileUpload(MultipartFile multipartFile){
        System.out.println("FileController.fileUpload");
        System.out.println("multipartFile = " + multipartFile);
        String result = fileService.fileUpload(multipartFile);
        return result;
    } // func end

    // [2] 다운로드
    // GET
    @GetMapping("/download")
    // GET , http://localhost:8080/file/download?fileName=ccfe0221-7a7b-44b2-a923-568c292e8a1f_add.js-improvement.md
    public void fileDownload (@RequestParam String fileName , HttpServletResponse response) {
        System.out.println("FileController.fileDownload");
        System.out.println("fileName = " + fileName);
        fileService.fileDownload(fileName, response);
    }
} // class end
