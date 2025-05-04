<%@ page import="java.util.Random" %><%
    Random rand = new Random();
    int n = rand.nextInt(90000) + 10000;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BuySphere E-Commerce</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script>
        const contextPath = "${pageContext.request.contextPath}";
    </script>
</head>
<body onload="showProducts()">

<main>

    <%@ include file="../Include/Header.jsp" %>

    <!-- Products Section -->
    <section class="Products py-5">
        <div class="container">
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-4 g-4" id="searchInsert">

            </div>
        </div>
    </section>

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
<script src="${pageContext.request.contextPath}/js/index.js?dummy=<%=n%>"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js" ></script>
</body>
</html>
