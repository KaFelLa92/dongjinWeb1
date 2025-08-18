<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 헤더 </title>
        <link rel="stylesheet" href="/css/common.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
    </head>

    <body>

        <div id="header">
            <ul id="sub-menu">
                <li> <a href="/index.jsp"> <img src="/img/logo.jpg" /> </a> </li>
                <li> <a href="/datago/data.jsp"> 공공데이터 </a> </li>
                <li> <a href="/kakao/map.jsp"> 카카오맵 </a> </li>
                <li> <a href="/kakao/datamap.jsp"> 데이터맵 </a> </li>
                <li> <a href="/example7/exam7.jsp"> 실습7 </a> </li>
            </ul>
            <ul id="log-menu"> <!-- 로그인 안 되어있을 때 메뉴 -->

            </ul>
        </div>

        <!-- JS 가져오기 -->
        <script src="/js/header.js"></script>

    </body>

    </html>