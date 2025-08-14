<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 회원목록조회/수정 </title>
        <link rel="stylesheet" href="retro.css">
    </head>

    <body class="page-list">

        <!-- 현재 JSP 파일내 다른 JSP 포함하기 -->
        <jsp:include page="/assessment/header.jsp"></jsp:include> <!-- 띄어쓰기 조심 -->

        <div class="container">
            <h3> 회원목록조회/수정 </h3>
            <table>
                <thead>
                    <tr>
                        <th> 회원번호 </th>
                        <th> 회원성명 </th>
                        <th> 전화번호 </th>
                        <th> 주소 </th>
                        <th> 가입일자 </th>
                        <th> 고객등급 </th>
                        <th> 거주지역 </th>
                    </tr>
                </thead>
                <tbody id="memberListTbody">

                </tbody>
            </table>
         </div>

        <jsp:include page="/assessment/footer.jsp"></jsp:include>

        <script src="list.js"></script>
       
    </body>

    </html>