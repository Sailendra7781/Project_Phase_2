<%
  String UserTypeID = (String)session.getAttribute("UserTypeID");
%>
<nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #2c3e50;">
  <div class="container-fluid d-flex justify-content-between">
    <a class="navbar-brand" href="/">BuySphere</a>

    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse justify-content-center" id="navbarSupportedContent">
      <div class="nav-item dropdown">
        <select name="productCategory" id="productCategory" class="form-control">
          <option value="">Categories</option>
          <%=com.example.BuySphere.Classes.CommonClasses.getCategoryDetails()%>
        </select>
      </div>
      <form class="d-flex" >
        <input class="form-control me-2" type="search" id="search" placeholder="Search" aria-label="Search">
        <button class="btn btn-primary" type="button" onclick="searchProduct()">Search</button>
      </form>
    </div>

    <div class="d-flex">
      <% if(UserTypeID != null && UserTypeID.equals("1")){ %>
      <a href="/addproduct" class="btn btn-outline-light me-2">Add Product</a>
      <a href="/products" class="btn btn-outline-light me-2">Product</a>
      <a href="/changepassword" class="btn btn-outline-light me-2">Change Password</a>
      <a href="/logout" class="btn btn-outline-light me-2">Logout</a>
      <% } %>

      <% if(UserTypeID != null && UserTypeID.equals("2")){ %>
      <a href="/products" class="btn btn-outline-light me-2">Product</a>
      <a href="/cart" class="btn btn-outline-light me-2">Cart</a>
      <a href="/changepassword" class="btn btn-outline-light me-2">Change Password</a>
      <a href="/logout" class="btn btn-outline-light me-2">Logout</a>
      <% } %>

      <% if(UserTypeID == null){%>
      <a href="/login" class="btn btn-outline-light me-2">Login</a>
      <a href="/register" class="btn btn-outline-light me-2">Signup</a>
      <% }%>
    </div>
  </div>
</nav>

<style>
  .navbar .container-fluid {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .navbar-collapse {
    display: flex;
    justify-content: center;
    width: 100%;
  }

  .navbar .d-flex {
    display: flex;
    justify-content: flex-end;
  }
</style>
