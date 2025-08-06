<%@ page language = "java" contentType = "text/html; charset = utf-8" pageEncoding = "utf-8" %>
<!DOCTYPE html>
<html>
<head>

</head>
<body>

    <h4> .JSP 파일내 .JSP 파일 포함하기 : html 안되는 기능 </h4>

    <jsp:include page ="/example/header.jsp"></jsp:include>
    
    <h3> 메인페이지 : http://localhost:8080/example/home.jsp </h3>
    <h3> 홈 파일 .jsp 입니다. </h3>

    <jsp:include page ="/example/footer.jsp"></jsp:include>

</body>
</html>