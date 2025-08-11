<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 자유게시판 </title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
        

    </head>

    <body>
        <div class="container mt-4">
            <jsp:include page="/board/header.jsp"></jsp:include>

            <div class="d-flex justify-content-between align-items-center mb-3">
                <h3>게시물 전체조회</h3>
                <a href="/board/write.jsp" class="btn btn-primary">글쓰기</a>
            </div>

            <table class="table table-hover text-center">
                <thead class="table-dark">
                    <tr>
                        <th style="width: 10%;">번호</th>
                        <th style="width: 70%;">내용</th>
                        <th style="width: 20%;">작성자</th>
                    </tr>
                </thead>
                <tbody id="boardTbody">
                    <!-- JS will populate this -->
                </tbody>
            </table>
        </div>
        <!-- js 불러오기 -->
        <script src='/board/list.js'></script>

    </body>

    </html>