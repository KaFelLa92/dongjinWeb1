<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 비밀번호 수정 </title>
        <link rel="stylesheet" href="/css/member/pwdupdate.css"
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
    </head>

    <body>

        <!-- 현재 JSP 파일내 다른 JSP 포함하기 -->
        <jsp:include page="/header.jsp"></jsp:include> <!-- 띄어쓰기 조심 -->

        <div class="login-container" id="container">
            <h2> 비밀번호 수정 페이지 </h2>
            <div> 기존 패스워드 : <input type="password" class="oldpwd" /> </div>
            <div> 새로운 패스워드 : <input type="password" class="newpwd" /> </div>
            <button type="button" onclick="onPwdUpdate()"> 비밀번호 변경 </button>
        </div>

        <script src="/js/member/pwdupdate.js"></script>

    </body>

    </html>