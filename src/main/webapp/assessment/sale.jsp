<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 회원매출조회 </title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
    </head>

    <body>

        <!-- 현재 JSP 파일내 다른 JSP 포함하기 -->
        <jsp:include page="/assessment/header.jsp"></jsp:include> <!-- 띄어쓰기 조심 -->

        <div>
            <h3> 회원매출조회 </h3>
            <table>
                <thead>
                    <tr>
                        <th> 회원번호 </th>
                        <th> 회원성명 </th>
                        <th> 고객등급 </th>
                        <th> 매출 </th>
                    </tr>
                </thead>
                <tbody id="memberSaleTbody">

                </tbody>
            </table>
         </div>

        <jsp:include page="/assessment/footer.jsp"></jsp:include>

        <script src="sale.js"></script>
       
    </body>

    </html>