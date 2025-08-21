<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 제품조회 </title>
        <link rel="stylesheet" href="/css/common.css"
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
    </head>

    <body>

        <!-- 현재 JSP 파일내 다른 JSP 포함하기 -->
        <jsp:include page="/header.jsp"></jsp:include> <!-- 띄어쓰기 조심 -->

        <div class="product-container" id="container">
            <div id="map" style="width:100%;height:800px;">

            </div>
            <div id="product">

            </div>
        </div>

        <!-- kakao APP JS 먼저 호출 -->
        <script type="text/javascript"
            src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8862611df14b8d0f1a6016b7e9a5ae24&libraries=clusterer"></script>

        <!-- JS 호출 -->
        <script src="/js/kakao/position.js"></script>
        <script src="/js/product/list.js"></script>

    </body>

    </html>