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
    <title>Product Details - BuySphere</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/editProduct.js?dummy=<%=n%>"></script>
    <script>
        const contextPath = "${pageContext.request.contextPath}";
    </script>
</head>
<body onload="showProductDetails(<%=ProductId%>)">
<%@ include file="../Include/Header.jsp" %>
<div class="container py-5">
    <div class="row" id="editProductDetails">

    </div>
</div>

<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js" ></script>
</body>
</html>
