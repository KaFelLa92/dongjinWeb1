package web.service;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

@Service // 해당 클래스를 스프링 컨테이너의 빈 등록
public class FileService {
    
    // [*] 업로드 경로
    // 1) 프로젝트내 src : 개발자가 코드를 작성하는 폴더
    // 2) 프로젝트내 build : 서버를 실행했을 때 컴파일된 코드 즉] 서버(24시간) 실행 폴더
    
    // 1. 현재 프로젝트의 최상위 디렉토리(폴더) 경로 찾기
    String baseDir = System.getProperty("user.dir");
    // 2. 방법2처럼 개발자 폴더가 아닌, 실행된 서버의 폴더로 업로드 경로 지정하기 , *개발환경*에 따라 달라진다.
    String uploadPath = baseDir + "/build/resources/main/static/upload/"; // upload 뒤에 / 꼭 넣기
    
    // [1] 파일 업로드 : 스프링에서는 MultipartFile 인터페이스 지원 ( 대용량 바이트 조작 )
    public String fileUpload(MultipartFile multipartFile) {
        // 1. 업로드한 파일명이 중복일 때, 다른 파일이어도 파일명이 같을 수 있다.
        // 방법1) 파일명 앞에 PK번호를 붙인다.
        // 방법2) 파일명 앞에 업로드된 날짜/시간을 붙인다.
        // 방법3) UUID(네트워크식별번호) 라이브러리 사용한다. UUID란? 난수 문자열 생성 (고유성 보장)
            // (1) 방법3처럼 UUID 생성한다.
        String uuid = UUID.randomUUID().toString();
            // (2) 업로드된 파일명과 합치기 , .getOriginalFilename() : 업로드된 파일명
            // - 업로드된 파일명의 언더바(_)가 존재하면 하이픈(-)으로 변경. 예] 짱구_사진.jpg => 짱구-사진.jpg
            // 이유 : uuid와 파일명 구분을 _언더바 사용하기 위한 내부적인 규칙
            // 예상 : 난수-난수-난수-난수-난수 _ 짱구-사진.jpg , UUID_파일명
        String fileName = uuid + "_" + multipartFile.getOriginalFilename().replaceAll("_","-");
        // .replaceAll( "기존문자" , "새로운문자" ) : 기존문자를 새로운문자로 변환
        // 2. 업로드 경로와 파일명 합치기 , 예상 : /build/resources/main/static/upload/짱구-사진.jpg
        String filePath = uploadPath + fileName;
        // 3. 업로드한 경로에 upload 폴더가 존재하지 않으면 폴더생성
        File pathFile = new File(uploadPath); // File 클래스 : 다양한 파일/폴더 함수를 제공
        if (!pathFile.exists()){    // .exist() : 지정한 경로가 존재하면 true 존재하지 않으면 false
            pathFile.mkdir(); // .mkdir() : 지정한 경로 생성 메소드
        }
        // 4. 업로드할 파일의 경로를 file 클래스 생성
        File uploardFile = new File(filePath);
        // 5. 업로드( 파일/바이트 이동) 하기 , .transferTo( file객체 ) : 지정한 file객체의 경로로 업로드 파일 이동
        try { // 일반예외 발생하므로 트라이캐치
            multipartFile.transferTo(uploardFile); // multipartFile(업로드된파일)
        } catch (IOException e) {
            System.out.println(e);
            return null; // 업로드 실패시 null 반환
        }
        // 6. 만일 업로드 성공시 파일이름 반환하기
        return fileName;
    } // method end
    

    // [2] 파일 다운로드
    public void fileDownload(String fileName , HttpServletResponse response){
        // 매개변수1 : String fileName 다운로드 받을 파일명
        // 매개변수2 : HttpServletResponse response 다운로드를 요청한 사용자의 *응답* 객체 @ResponseBody (요청한 사람이 응답받게 함)
        // 1. 다운로드 받을 파일명과 업로드 경로 조합
        String downloadPath = uploadPath + fileName;
        // 2. 만약에 지정한 경로에 파일이 없으면 리턴 , File 클래스란? 파일/폴더 관련 메소드/기능 제공
        File file = new File(downloadPath);
        if ( !file.exists() ){
            return; // 파일 없으면 함수 종료
        }
        // 3. 업로드할 파일을 자바(바이트)로 가져오기(파일읽기)
        try{ // 예외처리 필수
            // 3-1 파일 입력스트림 객체 생성
            FileInputStream fin = new FileInputStream(downloadPath);
            // 3-2 파일 용량만큼 배열 생성 , 읽어온 바이트들을 저장할 배열변수 선언
            long fileSize = file.length(); // 파일 용량 바이트 수
            byte[] bytes = new byte[(int)fileSize]; // 파일 용량만큼 바이트 배열변수 선언
            // 3-3 파일 입력스트림 객체로 파일 읽어와서 배열 저장
            fin.read(bytes);
            // 3-4 안전하게 스트림 닫기. 스트림이란? 바이트가 다니는 이동 통신 경로
            fin.close(); // 필수는 아니지만, 대용량에서는 안전하게 입력스트림 객체를 직접 닫는 걸 권장함

            // (*) 다운로드 형식 지정
            // 1. 파일의 uuid 제거, split("기준문자") : 문자열내 기준문자 기준으로 문자열 자르기
            String oldFilename = fileName.split("_")[1]; // _언더바 기준으로 잘라서 두번째 문자열
            // 2. 응답 형식 지정하기, 다운로드 형식과 다운로드파일지정 , URL은 한글 지원하지 않으므로 URLEncoder.encode( 파일명 , "UTF-8") 설정
            response.setHeader("Content-Disposition" , "attachment;filename=" + URLEncoder.encode(oldFilename , "UTF-8"));

        // 4. 업로드할 파일을 사용자에게 (response 객체 이용해서) 응답하기
            // 4-1 파일 출력스트림 객체 생성
            ServletOutputStream fout = response.getOutputStream(); // response 객체가 출력스트림 제공
            // 4-2 '3-3'에서 읽어온 바이트 배열을 다운로드 요청한 (response) 사용자에게 출력하기
            fout.write( bytes );
            // 4-3 안전하게 스트림 닫기
            fout.close();
        } catch (Exception e){
            System.out.println(e);
        }
    } // func end

    // [3] 파일 삭제
    public boolean fileDelete( String fileName ) {
        // 1. 삭제할 파일명과 업로드 경로 조합
        String filePath = uploadPath + fileName;
        // 2. 만약 경로에 파일이 존재하면 삭제
        File file = new File(filePath);
        if (file.exists()){
            file.delete(); // .delete() : 지정한 경로상의 파일 삭제
            return true;
        } // 2. 존재하지 않으면 false
        return false;

    } // func end

} // class end
