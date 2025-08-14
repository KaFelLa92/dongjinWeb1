<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 내 정보 </title>
        <link rel="stylesheet" href="/css/member/info.css"
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
    </head>

    <body>

        <!-- 현재 JSP 파일내 다른 JSP 포함하기 -->
        <jsp:include page="/header.jsp"></jsp:include> <!-- 띄어쓰기 조심 -->

        <div class="login-container" id="container">
            <h2> 회원가입 </h2>
            <div> 회원번호 : <span class="mno"> </span> </div>
            <div> 아이디 : <span class="mid"> </span></div>
            <div> 이름 : <span class="mname"> </span></div>
            <div> 연락처 : <span class="mphone"> </span></div>
            <div> 가입일 : <span class="mdate"> </span></div>
            <a href="/member/update.jsp"> 회원정보 수정 </a>
            <a href="/member/pwdupdate.jsp"> 비밀번호 수정 </a>
            <a href="#" onclick="onDelete()"> 회원 탈퇴 </a>
        </div>

        <script src="/js/member/info.js"></script>

    </body>

    </html>