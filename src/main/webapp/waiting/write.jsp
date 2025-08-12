<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 등록페이지 </title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
    </head>

    <body>

        <jsp:include page="/waiting/header.jsp"></jsp:include>
        <div>
            <h3> 대기번호등록 </h3>
            연락받으실 번호 : <input type="text" class="wnumber" />
            인원 수 : <input type="number" class="wcount" />
            <button type="button" onclick="waitWrite()"> 대기 등록 </button>
        </div>

        <!-- JS 호출 -->
        <script src="/waiting/write.js"></script>

    </body>

    </html>