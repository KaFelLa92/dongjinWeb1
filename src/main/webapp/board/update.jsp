<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 게시물 수정 </title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
        
    </head>

    <body>
        <div class="container mt-4">
            <jsp:include page="/board/header.jsp"></jsp:include>
            <h3 class="my-4">게시물 수정</h3>
            <div class="mb-3">
                <label for="bcontent" class="form-label">수정할 내용</label>
                <textarea class="form-control bcontent" id="bcontent" rows="10"></textarea>
            </div>
            <div class="d-flex justify-content-end">
                <button type="button" class="btn btn-primary" onclick="boardUpdate()">수정 완료</button>
                <a href="/board/list.jsp" class="btn btn-secondary ms-2">취소</a>
            </div>
        </div>

        <!-- js 불러오기 -->
        <script src='/board/update.js'></script>

    </body>

    </html>