<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset='utf-8'>
        <title> 데이터 </title>
        <link rel="stylesheet" href="/css/common.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css">
    </head>

    <body>

        <!-- 현재 JSP 파일내 다른 JSP 포함하기 -->
        <jsp:include page="/header.jsp"></jsp:include> <!-- 띄어쓰기 조심 -->

        <div id="container" class="data-container">

            <h3> [2] 사업자 상태 조회 </h3>
            <input type="text" class="b_no" placeholder="하이픈(-)없이 사업자번호 입력"/>
            <button type="button" onclick="dataAPI2()"> 확인 </button>

            <h3> [1] 인천 부평구 주유소 현황 API </h3>
            <table>
                <thead>
                    <tr>
                        <td> 연번 </td>
                        <td> 상호 </td>
                        <td> 업종 </td>
                        <td> 전화번호 </td>
                        <td> 주소 </td>
                    </tr>
                </thead>
                <tbody id="dataTbody">

                </tbody>
            </table>

        </div>

        <!-- JS 가져오기 -->
        <script src="/js/datago/data.js"></script>

    </body>

    </html>