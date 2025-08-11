<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 자유게시판 </title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
        

    </head>

    <body>
        <div class="container">
            <jsp:include page="/board/header.jsp"></jsp:include>
        </div>
        <div>
            <h3> 게시물 전체조회 페이지 </h3>
            <a href="/board/write.jsp"> 글쓰기 </a>
            <table border="1">
                <tr>
                    <th> 번호 </th>
                    <th> 내용 </th>
                    <th> 작성자 </th>
                </tr>
                <tbody id="boardTbody">
                    <tr>
                        <th> 내용 </th>
                    </tr>

                </tbody>
            </table>
        </div>
        <!-- js 불러오기 -->
        <script src='/board/list.js'></script>

    </body>

    </html>