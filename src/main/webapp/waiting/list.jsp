<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>대기 현황</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; }
        .table-hover tbody tr:hover {
            background-color: #e9ecef;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <jsp:include page="/waiting/header.jsp"></jsp:include>
    <div class="container mt-5">
        <div class="card shadow-sm">
            <div class="card-header bg-primary text-white d-flex justify-content-between align-items-center">
                <h3>대기 현황</h3>
                <a href="/waiting/write.jsp" class="btn btn-light btn-sm">대기 등록하기</a>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-hover align-middle text-center">
                        <thead class="table-light">
                            <tr>
                                <th>대기번호</th>
                                <th>연락처</th>
                                <th>인원 수</th>
                                <th>등록일시</th>
                            </tr>
                        </thead>
                        <tbody id="waitTbody">
                            <!-- JS로 채워질 부분 -->
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <script src="list.js"></script>
</body>
</html>