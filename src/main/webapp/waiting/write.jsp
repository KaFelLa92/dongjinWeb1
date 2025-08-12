<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>대기 등록</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .container { max-width: 600px; }
    </style>
</head>
<body>
    <jsp:include page="/waiting/header.jsp"></jsp:include>
    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white text-center">
                <h3>대기 등록</h3>
            </div>
            <div class="card-body p-4">
                <div class="mb-3">
                    <label for="wnumber" class="form-label">연락처</label>
                    <input type="text" class="form-control wnumber" id="wnumber" placeholder="010-1234-5678">
                </div>
                <div class="mb-4">
                    <label for="wcount" class="form-label">인원 수</label>
                    <input type="number" class="form-control wcount" id="wcount" placeholder="방문 인원 수를 입력하세요">
                </div>
                <div class="d-grid">
                    <button type="button" onclick="waitWrite()" class="btn btn-primary btn-lg">등록하기</button>
                </div>
            </div>
        </div>
    </div>
    <script src="write.js"></script>
</body>
</html>