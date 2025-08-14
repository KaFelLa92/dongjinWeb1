<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 회원가입 페이지 </title>
        <link rel="stylesheet" href="/css/member/signup.css"
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
    </head>

    <body>

        <!-- 현재 JSP 파일내 다른 JSP 포함하기 -->
        <jsp:include page="/header.jsp"></jsp:include> <!-- 띄어쓰기 조심 -->

        <div class="login-container" id="container">
            <h2> 회원가입 </h2>
            아이디 : <input type="text" onkeyup="idcheck()" class="idInput form-group" id="idInput" placeholder="아이디입력" /> <br/ >
            <div class="idcheck"> </div> <br/>
            패스워드 : <input type="password" class="pwdInput form-group" id="pwdInput" placeholder="패스워드입력" /> <br />
            이름 : <input type="text" class="nameInput form-group" id="nameInput" placeholder="성함입력" /> <br />
            연락처 : <input type="text" onkeyup="phonecheck()" class="phoneInput form-group" id="phoneInput" placeholder="연락처입력" /> <br />
            <div class="phonecheck" > </div> <br/>
            <button class="login-btn" type="button" onclick="signup()"> 회원가입 </button> <br />
            <a class="sub-links" href="/member/login.jsp"> 로그인하기 </a>
            <a class="sub-links" href="/member/find.jsp"> 아이디/비밀번호찾기 </a>
        </div>

        <script src="/js/check/check.js"></script>
        <script src="/js/member/signup.js"></script>

    </body>

    </html>