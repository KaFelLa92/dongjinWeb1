<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>월정보말칼국수 대기 정보 수정</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f4f4f4;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .card {
            width: 100%;
            max-width: 500px;
            border-radius: 15px;
        }
        .card-header {
            background-color: #007bff;
            color: white;
            text-align: center;
            border-top-left-radius: 15px;
            border-top-right-radius: 15px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="card shadow">
            <div class="card-header py-3">
                <h2>대기 정보 수정</h2>
            </div>
            <div class="card-body p-4">
                <p class="text-center text-muted mb-4">월정보말칼국수 대기 정보를 수정합니다.</p>
                <div class="mb-3">
                    <label for="wnumber" class="form-label"><strong>연락처</strong></label>
                    <input type="text" class="form-control wnumber" id="wnumber" placeholder="예: 010-1234-5678">
                </div>
                <div class="mb-4">
                    <label for="wcount" class="form-label"><strong>인원 수</strong></label>
                    <input type="number" class="form-control wcount" id="wcount" placeholder="예: 2">
                </div>
                <div class="d-grid gap-2">
                    <button type="button" onclick="waitUpdate()" class="btn btn-primary btn-lg">수정 완료</button>
                    <a href="/waiting/index.jsp" class="btn btn-outline-secondary">처음으로</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Custom JS for update logic -->
    <script src="/waiting/update.js"></script>
</body>
</html>
