<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 대기상세조회 페이지 </title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
    </head>

    <body>

        <jsp:include page="/waiting/header.jsp"></jsp:include>
        <div>
            <h3> 대기현황상세조회 </h3>
            대기번호 : <div class="wnoBox"></div>
            연락처 : <div class="wnumberBox"></div>
            인원 수 : <div class="wcountBox"></div>
            등록일시 : <div class="wdateBox"></div>
            <button type="button" onclick="waitToUpdate()"> 수정 </button>
            <button type="button" onclick="waitDelete()"> 삭제 </button>
        </div>
        <!-- JS 호출 -->
        <script src="/waiting/view.js"></script>

    </body>

    </html>