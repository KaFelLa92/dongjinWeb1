<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 회원정보 수정 </title>
        <link rel="stylesheet" href="/css/member/update.css"
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
    </head>

    <body>

        <!-- 현재 JSP 파일내 다른 JSP 포함하기 -->
        <jsp:include page="/header.jsp"></jsp:include> <!-- 띄어쓰기 조심 -->

        <div class="login-container" id="container">
            <h2> 수정 페이지 </h2>
            <div> 회원번호 : <span class="mno"> </span> </div>
            <div> 아이디 : <span class="mid"> </span></div>
            <div> 이름 : <input class="mname" /> </div>
            <div> 연락처 : <input class="mphone phoneInput" type="text" onkeyup="phonecheck()" /> </div>
            <div class="phonecheck"> </div> <br />
            <button type="button" onclick="onUpdate()"> 정보 변경 </button>
        </div>

        <script src="/js/check/check.js"></script>
        <script src="/js/member/update.js"></script>

    </body>

    </html>