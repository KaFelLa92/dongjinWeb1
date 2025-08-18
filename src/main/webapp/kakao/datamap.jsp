<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 공공데이터+카카오맵 </title>
        <link rel="stylesheet" href="/css/common.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
    </head>

    <body>

        <jsp:include page="/header.jsp"></jsp:include>
        <div id="container" class="map-container">
            <div id="map"></div>
            <div id="sidebar"></div>

        </div>

        <!-- kakao APP JS 먼저 호출 -->
        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8862611df14b8d0f1a6016b7e9a5ae24&libraries=clusterer"></script>

        <!-- JS 가져오기 -->
        <script src="/js/kakao/datamap.js"></script>

    </body>

    </html>