package example.day02._3실습;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 1. 스프링부트어플리케이션 어노테이션
public class AppStart {
    public static void main(String[] args) {
        // 2. SpringApplication.run(AppStart.class) 생성
        SpringApplication.run(AppStart.class);

        /*
        Spring 실습1 : 게시판 RestController 만들기
        1. AppStart 클래스 생성    2. BoardController 클래스 생성
        3. 각 URL 매핑 만들기

        1. 글쓰기           POST       "/exam1/board"
        요청자료 : x , 응답자료 : true/false

        2. 전체 글 조회      GET        "/exam1/board"
        요청자료 : x , 응답자료 : 임의의 List 타입 ,  [ {bno:'',btitle:''} ,  {bno:'',btitle:''}  ] , 아래 참고

        3. 개별 글 조회      GET        "/exam1/board/view"
        요청자료 : x , 응답자료 : 임의의 MAP  타입  ,  {bno:'',btitle:''} , 아래 참고

        4. 개글 글 수정      PUT        "/exam1/board"
        요청자료 : x , 응답자료 : true 또는 false

        5. 개별 글 삭제      DELETE     "/exam1/board"
        요청자료 : x , 응답자료 : 임의의 삭제한 번호 , 3
        제출 :
        1. 카카오톡방에 ip 제출 ( 강사가 체크 할때 까지 서버 켜주세요 )
        2. itdanja.com에 과제 코드 git 링크 제출
        */

    }   // main end
}   // class end
