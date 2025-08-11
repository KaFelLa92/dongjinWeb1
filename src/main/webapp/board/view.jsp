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
           <h3> 게시물 상세 페이지 </h3>
           작성자 : <div class="bwriterBox"></div>
           내용 : <div class="bcontentBox"></div>
           <button type="button" onclick="boardUpdateView()"> 수정 </button>
           <button type="button" onclick="boardDelete()"> 삭제 </button>
        </div>

        <!-- js 불러오기 -->
        <script src='/board/view.js'></script>

    </body>

    </html>