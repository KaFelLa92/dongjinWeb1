<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 대기전체조회 페이지 </title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
    </head>

    <body>

        <jsp:include page="/waiting/header.jsp"></jsp:include>
        <div>
            <h3> 대기현황전체조회 </h3>
            <a href="/waiting/write.jsp"> 대기등록하기 </a>
        </div>
        <table>
            <thead>
                <tr>
                    <th> 대기번호 </th>
                    <th> 연락처 </th>
                    <th> 인원 수 </th>
                    <th> 등록일시 </th>
                </tr>
            </thead>
            <tbody id="waitTbody">
                <!-- JS 자리 -->
            </tbody>
        </table>
        <!-- JS 호출 -->
        <script src="/waiting/list.js"></script>

    </body>

    </html>