<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title>Save</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css">

    </head>

    <body>

        <jsp:include page="/student/header.jsp"></jsp:include>

        <div>
            <h3> 등록 페이지 </h3>
            이름 : <input type="text" class="sname" /> </br>
            국어점수 : <input type="text" class="skor" /> </br>
            수학점수 : <input type="text" class="smath" /> </br>
            <button type="button" onclick="save()"> 등록 </button>
        </div>
        
        <script src='/student/save.js'></script>
    </body>

    </html>