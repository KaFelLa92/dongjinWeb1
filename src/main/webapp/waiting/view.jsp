<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>대기 상세 정보</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .container { max-width: 700px; }
        .list-group-item {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .list-group-item strong {
            margin-right: auto;
        }
    </style>
</head>
<body>
    <jsp:include page="/waiting/header.jsp"></jsp:include>
    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white text-center">
                <h3>대기 상세 정보</h3>
            </div>
            <div class="card-body p-4">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">
                        <strong>대기번호</strong>
                        <span class="badge bg-primary rounded-pill fs-6 wnoBox"></span>
                    </li>
                    <li class="list-group-item">
                        <strong>연락처</strong>
                        <span class="fs-6 wnumberBox"></span>
                    </li>
                    <li class="list-group-item">
                        <strong>인원 수</strong>
                        <span class="fs-6 wcountBox"></span>
                    </li>
                    <li class="list-group-item">
                        <strong>등록일시</strong>
                        <span class="fs-6 wdateBox"></span>
                    </li>
                </ul>
            </div>
            <div class="card-footer bg-light text-end">
                <button type="button" onclick="waitToUpdate()" class="btn btn-warning">수정</button>
                <button type="button" onclick="waitDelete()" class="btn btn-danger">삭제</button>
                <a href="/waiting/list.jsp" class="btn btn-secondary">목록으로</a>
            </div>
        </div>
    </div>
    <script src="view.js"></script>
</body>
</html>