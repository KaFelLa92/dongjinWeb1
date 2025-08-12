<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 쇼핑몰 회원관리 </title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
    </head>

    <body>

        <!-- 현재 JSP 파일내 다른 JSP 포함하기 -->
        <jsp:include page="/assessment/header.jsp"></jsp:include> <!-- 띄어쓰기 조심 -->

        <div>
            <h3> 쇼핑몰 회원관리 프로그램 </h3>
            <p> 쇼핑몰 회원정보와 회원매출정보 데이터베이스를 구축하고 회원관리 프로그램을 작성하는 프로그램이다. </p>
        </div>

        <jsp:include page="/assessment/footer.jsp"></jsp:include>

    </body>

    </html>