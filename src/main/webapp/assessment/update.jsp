<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 회원 정보 수정 </title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
    </head>

    <body>

        <!-- 현재 JSP 파일내 다른 JSP 포함하기 -->
        <jsp:include page="/assessment/header.jsp"></jsp:include> <!-- 띄어쓰기 조심 -->

        <div>
            <h3> 홈쇼핑 회원 정보 수정 </h3>
            회원번호(자동발생) <input class="nextNo custno" type="number" readonly/> <!-- 밸류 있어야함. 수정 불가 -->
            회원성명 <input class="custname" type="text" />
            회원전화 <input class="phone" type="text" />
            회원주소 <input class="address" type="text"/>
            가입일자 <input class="date" type="text" readonly/> <!-- 밸류 있어야함. 수정 불가 -->
            고객등급[A:VIP,B:일반,C:직원] <input class="grade" type="text" />
            도시코드 <input class="city" type="text"/>
            <button type="button" onclick="memberUpdate()"> 수정 </button>
            <button type="button" onclick="memberToList()"> 조회 </button>
         </div>

        <jsp:include page="/assessment/footer.jsp"></jsp:include>

        <script src="update.js"></script>
    </body>

    </html>