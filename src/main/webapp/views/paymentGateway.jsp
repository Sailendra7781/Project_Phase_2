<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment - BuySphere</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script>
        const contextPath = "${pageContext.request.contextPath}";
    </script>
    <script src="../js/jquery.min.js"></script>
    <script src="../js/payment.js"></script>
    <script src="../js/Javascript.js"></script>
</head>
<body>
<%@ include file="../Include/Header.jsp" %>
<div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
    <div class="card p-4" style="max-width: 400px; width: 100%;">
        <h2 class="text-center mb-4">Payment</h2>
        <form>
            <div class="mb-3">
                <label for="cdCard" class="form-label">Credit/Debit Card</label>
                <input type="text" class="form-control" id="cdCard" placeholder="Enter your Credit/Debit Card Number">
            </div>
            <div class="row mb-3">
                <div class = "col-6">
                    <label for="expDate" class="form-label">Expiry Date</label>
                    <input type="text" class="form-control" id="expDate" placeholder="MM/YY">
                </div>
                <div class = "col-6">
                    <label for="cvv" class="form-label">CVV</label>
                    <input type="text" class="form-control" id="cvv" placeholder="">
                </div>
            </div>
            <button type="button" class="btn btn-primary w-100" onclick="pay()">Pay</button>
        </form>
    </div>
</div>
</body>
</html>
