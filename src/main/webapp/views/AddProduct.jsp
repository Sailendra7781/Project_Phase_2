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
    <title>Add Product - BuySphere</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script>
        const contextPath = "${pageContext.request.contextPath}";
    </script>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/AddProduct.js?dummy=<%=n%>"></script>
    <script src="${pageContext.request.contextPath}/js/Javascript.js?dummy=<%=n%>"></script>
</head>
<body>
<%@ include file="../Include/Header.jsp" %>
<div class="container d-flex justify-content-center align-items-center">
    <div class="card p-4" style="max-width: 600px; width: 100%;">
        <h2 class="text-center mb-4">Add Product</h2>
        <form>
            <div class="mb-3">
                <label for="ProductCategory" class="form-label">Product Category</label>
                <select name="productCategory" id="ProductCategory" class="form-control">
                    <option value="">Select Category</option>
                    <%=com.example.BuySphere.Classes.CommonClasses.getCategoryDetails()%>
                </select>
            </div>
            <div class="mb-3">
                <label for="productName" class="form-label">Product Name</label>
                <input type="text" class="form-control" id="productName" placeholder="Enter Product Name">
            </div>
            <div class="mb-3">
                <label for="productPrice" class="form-label">Product Price</label>
                <input type="number" class="form-control" id="productPrice" placeholder="Enter Product Price" step="0.01">
            </div>
            <div class="mb-3">
                <label for="productQuantity" class="form-label">Product Quantity</label>
                <input type="number" class="form-control" id="productQuantity" placeholder="Enter Product Quantity">
            </div>
            <div class="mb-3">
                <label for="productDescription" class="form-label">Product Description</label>
                <textarea class="form-control" id="productDescription" rows="4" placeholder="Enter Product Description"></textarea>
            </div>
            <div class="mb-3">
                <label for="fileUpload" class="form-label">Upload File</label>
                <input type="file" class="form-control" id="fileUpload"/>
            </div>
            <button type="button" class="btn btn-primary w-100" onclick="addProduct()">Add Product</button>
        </form>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>

</body>
</html>