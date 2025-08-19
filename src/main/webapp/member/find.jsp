<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 아이디비밀번호찾기 </title>
        <link rel="stylesheet" href="/css/member/pwdupdate.css"
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
    </head>

    <body>

        <!-- 현재 JSP 파일내 다른 JSP 포함하기 -->
        <jsp:include page="/header.jsp"></jsp:include> <!-- 띄어쓰기 조심 -->

        <div class="login-container" id="container">
            <div class="container">
                <h2> 아이디 찾기 </h2>
                <div> 이름 : <input type="text" class="mname" /> </div>
                <div> 연락처 : <input type="text" class="mphone" /> </div>
                <button type="button" class="login-btn" onclick="findId()"> 아이디 확인 </button>
            </div>
            <div class="container">
                <h2> 비밀번호 찾기 </h2>
                <div> 아이디 : <input type="text" class="mid" /> </div>
                <div> 연락처 : <input type="text" class="mphone2" /> </div>
                <button type="button" class="login-btn" onclick="findPwd()"> 비밀번호 확인 </button>
            </div>

        </div>

        <script src="/js/member/find.js"></script>

    </body>

    </html>