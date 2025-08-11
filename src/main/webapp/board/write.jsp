<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 게시물 작성 </title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
        

    </head>

    <body>
        <div class="container">
            <jsp:include page="/board/header.jsp"></jsp:include>
        </div>
        <div>
           <h3> 게시물 쓰기 페이지 </h3>
           내용 : <textarea class="bcontent"></textarea> <br />
           작성자 : <input class="bwriter" type="text" /> <br />
           <button type="button" onclick="boardWrite()"> 등록 </button>
        </div>

        <!-- js 불러오기 -->
        <script src='/board/write.js'></script>

    </body>

    </html>