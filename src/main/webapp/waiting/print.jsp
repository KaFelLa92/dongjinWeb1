<%@ page language="java" contentType="text/html; charset = utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title>대기 등록 메인 페이지</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css">
</head>
<body>

    <jsp:include page="/waiting/header.jsp"></jsp:include>

    <div>
        <h3> 대기현황조회 페이지입니다. </h3>
        <table>
            <thead>
                <tr>
                    <th> 대기번호 </th>
                    <th> 연락처 </th>
                    <th> 인원 수 </th>
                </tr>
            </thead>
            <tbody id="waitingTbody"> 

            </tbody>
        </table>
    </div>


    <script src='print.js'></script>
</body>
</html>