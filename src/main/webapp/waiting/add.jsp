<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title> 대기번호등록 </title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css">
</head>
<body>

    <jsp:include page="/waiting/header.jsp"></jsp:include>

    <div>
        <h3> 대기등록 페이지입니다. </h3>
        전화번호 : <input type="text" class="wnumber" /> </br>
        인원 수 : <input type="number" class="wcount" /> </br>
        <button type="button" onclick="waitAdd()"> 대기번호등록 </button>
    </div>

    <script src='add.js'></script>
</body>
</html>