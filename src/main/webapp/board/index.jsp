<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 인덱스 페이지 </title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
        

    </head>

    <body>
        <div class="container py-4">
            <jsp:include page="/board/header.jsp"></jsp:include>
            <div class="p-5 mb-4 bg-light rounded-3">
                <div class="container-fluid py-5">
                    <h1 class="display-5 fw-bold">비회원제 자유게시판</h1>
                    <p class="col-md-8 fs-4">이곳은 누구나 자유롭게 글을 작성하고 공유할 수 있는 공간입니다.</p>
                    <a href="/board/list.jsp" class="btn btn-primary btn-lg" type="button">게시판으로 이동</a>
                </div>
            </div>
            <footer class="pt-3 mt-4 text-muted border-top">
                &copy; 2025
            </footer>
        </div>
    </body>

    </html>