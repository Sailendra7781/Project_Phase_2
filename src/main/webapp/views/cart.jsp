<%@ page import="java.util.Random" %><%
    Random rand = new Random();
    int n = rand.nextInt(90000) + 10000;

    String userID = (String)session.getAttribute("userID");
    if(userID == null){
        response.sendRedirect("/");
    }
    String ProductId = request.getParameter("productID");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart - BuySphere</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/cart.js?dummy=<%=n%>"></script>
    <script>
        const contextPath = "${pageContext.request.contextPath}";
    </script>
</head>
<body onload="showCartDetails()">
<%@ include file="../Include/Header.jsp" %>
<form name="payment" id="payment" method="post">
<div class="container mt-5" id="cart">

</div>
</form>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js" ></script>
</body>
</html>
