<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>월정보말칼국수 대기 시스템</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .hero-section {
            background: url('https://source.unsplash.com/1600x900/?restaurant,food') no-repeat center center;
            background-size: cover;
            color: white;
            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.6);
        }
    </style>
</head>
<body>
    <jsp:include page="/waiting/header.jsp"></jsp:include>

    <div class="container-fluid p-0">
        <div class="p-5 text-center hero-section">
            <h1 class="display-4 fw-bold">월정보말칼국수</h1>
            <p class="fs-4">방문해주셔서 감사합니다. 아래 버튼으로 대기를 등록하거나 현황을 확인하세요.</p>
        </div>
    </div>

    <div class="container mt-5 text-center">
        <div class="row">
            <div class="col-md-6 mb-3">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">대기 등록하기</h5>
                        <p class="card-text">새로운 대기를 등록합니다.</p>
                        <a href="/waiting/write.jsp" class="btn btn-primary btn-lg">등록 페이지로</a>
                    </div>
                </div>
            </div>
            <div class="col-md-6 mb-3">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <h5 class="card-title">대기 현황 보기</h5>
                        <p class="card-text">현재 대기 목록을 확인합니다.</p>
                        <a href="/waiting/list.jsp" class="btn btn-secondary btn-lg">현황 페이지로</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>