<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 로그인 페이지 </title>
        <link rel="stylesheet" href="/css/member/login.css"
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
    </head>

    <body>

        <!-- 현재 JSP 파일내 다른 JSP 포함하기 -->
        <jsp:include page="/header.jsp"></jsp:include> <!-- 띄어쓰기 조심 -->


        <div class="login-container" id="container">
            <h2> 로그인 </h2>
            아이디 : <input type="text" class="idInput form-group" id="idInput" placeholder="아이디입력" />
            패스워드 : <input type="password" class="pwdInput form-group" id="pwdInput" placeholder="패스워드입력" />
            <button class="login-btn" type="button" onclick="login()"> 로그인 </button> <br />
            <a class="sub-links" href="/member/signup.jsp"> 회원가입하기 </a>
            <a class="sub-links" href="/member/find.jsp"> 아이디/비밀번호찾기 </a>
        </div>

        <script src="/js/member/login.js"></script>

    </body>

    </html>