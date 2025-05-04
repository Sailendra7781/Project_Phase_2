<%@ page import="java.util.Random" %><%
  Random rand = new Random();
  int n = rand.nextInt(90000) + 10000;
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login - BuySphere</title>
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <script>
    const contextPath = "${pageContext.request.contextPath}";
  </script>
  <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/login.js?dummy=<%=n%>"></script>
  <script src="${pageContext.request.contextPath}/js/Javascript.js?dummy=<%=n%>"></script>
</head>
<body>
<%@ include file="../Include/Header.jsp" %>
<div class="container d-flex justify-content-center align-items-center" style="height: 100vh;">
  <div class="card p-4" style="max-width: 400px; width: 100%;">
    <h2 class="text-center mb-4">Login</h2>
    <form>
      <div class="mb-3">
        <label for="username" class="form-label">Username</label>
        <input type="email" class="form-control" id="username" placeholder="Enter your Username">
      </div>
      <div class="mb-3">
        <label for="password" class="form-label">Password</label>
        <input type="password" class="form-control" id="password" placeholder="Enter your password">
      </div>
      <button type="button" class="btn btn-primary w-100" onclick="login()">Login</button>
    </form>
    <div class="mt-3 text-center">
      <p><a href="/register">Don't have an account? Sign Up</a></p>
    </div>
  </div>
</div>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js" ></script>
</body>
</html>
