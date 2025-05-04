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
  <title>Change Password - BuySphere</title>
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  <script>
    const contextPath = "${pageContext.request.contextPath}";
  </script>
  <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/js/changePassword.js?dummy=<%=n%>"></script>
  <script src="${pageContext.request.contextPath}/js/Javascript.js?dummy=<%=n%>"></script>
</head>
<body>

<main>

  <%@ include file="../Include/Header.jsp" %>

  <!-- Change Password Form Section as a Card -->
  <div class="container mt-5">
    <div class="card shadow-lg p-4">
      <h2 class="text-center mb-4">Change Password</h2>
      <form action="${pageContext.request.contextPath}/changePassword" method="post">
        <div class="mb-3">
          <label for="currentPassword" class="form-label">Current Password</label>
          <input type="password" class="form-control" id="currentPassword" name="currentPassword" required>
        </div>
        <div class="mb-3">
          <label for="newPassword" class="form-label">New Password</label>
          <input type="password" class="form-control" id="newPassword" name="newPassword" required>
        </div>
        <div class="mb-3">
          <label for="reEnterPassword" class="form-label">Re-enter New Password</label>
          <input type="password" class="form-control" id="reEnterPassword" name="reEnterPassword" required>
        </div>
        <button type="button" class="btn btn-primary" onclick="changePassword()">Change Password</button>
      </form>
    </div>
  </div>

  <!-- Footer Section -->
  <footer class="bg-dark text-white py-4">
    <div class="container">
      <div class="row">
        <div class="col-12 text-center">
          <h5>About Us</h5>
          <p>BuySphere is your one-stop shop for all things electronic, fashionable, and home appliances. Explore a wide range of products at unbeatable prices.</p>
        </div>
      </div>
    </div>
  </footer>

</main>

<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
