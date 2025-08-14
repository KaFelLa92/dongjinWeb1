<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 회원 등록 </title>
        <link rel="stylesheet" href="retro.css">
    </head>

    <body class="page-add">

        <!-- 현재 JSP 파일내 다른 JSP 포함하기 -->
        <jsp:include page="/assessment/header.jsp"></jsp:include> <!-- 띄어쓰기 조심 -->

        <div class="container">
            <h3> 홈쇼핑 회원 등록 </h3>
            <form>
                회원번호(자동발생): <input class="nextNo" type="number" readonly/> <br/><br/>
                회원성명: <input class="custname" type="text" /> <br/><br/>
                회원전화: <input class="phone" type="text" /> <br/><br/>
                회원주소: <input class="address" type="text"/> <br/><br/>
                가입일자: <input class="date" type="text" readonly/> <br/><br/>
                고객등급[A:VIP,B:일반,C:직원]: <input class="grade" type="text" /> <br/><br/>
                도시코드: <input class="city" type="text"/> <br/><br/>
                <p style="text-align:center;">
                    <button type="button" onclick="memberAdd()"> 등록 </button>
                    <button type="button" onclick="memberToList()"> 조회 </button>
                </p>
            </form>
         </div>

        <jsp:include page="/assessment/footer.jsp"></jsp:include>

        <script src="add.js"></script>
    </body>

    </html>