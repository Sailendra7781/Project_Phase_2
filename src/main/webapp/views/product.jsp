<%@ page import="java.util.Random" %><%
  Random rand = new Random();
  int n = rand.nextInt(90000) + 10000;

  String userID = (String)session.getAttribute("userID");
  if(userID == null){
    response.sendRedirect("/");
  }
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Product Details - BuySphere</title>
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/products.js?dummy=<%=n%>"></script>

  <script>
    const contextPath = "${pageContext.request.contextPath}";
  </script>
</head>
<body onload="getProductDetails()">
<%@ include file="../Include/Header.jsp" %>
<form name="SubmitForm" id="SubmitForm">


<div class="container py-5">
  <div class="row" id="productDetails">

  </div>
  <button type="button" class="btn btn-success" id="cart" onclick="gotToCart()">Proceed to Cart</button>
</div>

<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js" ></script>
<input type="hidden" name="productID" id ="productID">
</form>
</body>
</html>
