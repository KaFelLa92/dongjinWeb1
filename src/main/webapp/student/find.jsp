<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title>Find</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css">

    </head>

    <body>

        <jsp:include page="/student/header.jsp"></jsp:include>

        <div>
            <h3> 전체조회 페이지 </h3>
            <table>
                <thead>
                    <tr>
                        <th> 번호 </th>
                        <th> 이름 </th>
                        <th> 국어 </th>
                        <th> 수학 </th>
                    </tr>
                </thead>
                <tbody id="studentTbody">

                </tbody>
            </table>

        </div>

        <script src='/student/find.js'></script>
    </body>

    </html>