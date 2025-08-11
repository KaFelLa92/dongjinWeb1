<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 게시물 상세 </title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
        
    </head>

    <body>
        <div class="container mt-4">
            <jsp:include page="/board/header.jsp"></jsp:include>
            <h3 class="my-4">게시물 상세 정보</h3>
            <div class="card">
                <div class="card-header">
                    작성자: <span class="bwriterBox fw-bold"></span>
                </div>
                <div class="card-body">
                    <p class="card-text bcontentBox" style="min-height: 150px;"></p>
                </div>
            </div>
            <div class="mt-3 d-flex justify-content-end">
                <button type="button" class="btn btn-warning me-2" onclick="boardUpdateView()">수정</button>
                <button type="button" class="btn btn-danger me-2" onclick="boardDelete()">삭제</button>
                <a href="/board/list.jsp" class="btn btn-secondary">목록</a>
            </div>
        </div>

        <!-- js 불러오기 -->
        <script src='/board/view.js'></script>

    </body>

    </html>