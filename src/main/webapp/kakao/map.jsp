<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 카카오맵 </title>
        <link rel="stylesheet" href="/css/common.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
    </head>

    <body>

        <jsp:include page="/header.jsp"></jsp:include>
        <div id="container">

            <!-- KAKAO 맵 출력되는 DIV -->
            <div id="map" style="width:500px;height:400px;"></div>

        </div>

        <!-- KAKAO 지도 API JS : 개발자정의 JS보다 먼저 호출 권장 -->
        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1ac4a57d8a5927d34020a891fcdbbcbd"></script>
        <!-- JS 가져오기 -->
        <script src="/js/kakao/map.js"></script>

    </body>

    </html>