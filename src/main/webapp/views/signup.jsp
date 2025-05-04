<%@ page import="java.util.Random" %><%
Random rand = new Random();
int n = rand.nextInt(90000) + 10000;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>SignUp - BuySphere</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <script>
        const contextPath = "${pageContext.request.contextPath}";
    </script>
    <style>
        body {
            font-family: "Quicksand", sans-serif;
            background-color: #f4f6f9;
            padding-top: 70px; /* Space for fixed navbar */
        }

        h2 {
            font-family: "Teko", sans-serif;
            font-weight: bold;
            color: #333;
        }

        .card {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .card p {
            font-size: 14px;
            color: #555;
        }

        .card a {
            text-decoration: none;
            color: #007bff;
        }

        .card a:hover {
            text-decoration: underline;
        }

        .card .form-control {
            font-size: 14px;
        }

        .card .btn {
            font-size: 16px;
        }

        /* Custom Styles for Form Fields */
        .form-label {
            font-weight: 500;
        }

        /* Navbar */
        .navbar {
            position: fixed;
            width: 100%;
            top: 0;
            left: 0;
            z-index: 9999;
            background-color: #2c3e50;
            padding: 10px 0;
        }

        .navbar .navbar-brand {
            font-size: 1.5rem;
            font-weight: bold;
        }

        .navbar .navbar-nav .nav-link {
            font-size: 1rem;
        }

        .container-fluid {
            max-width: 1200px;
        }

        .card {
            max-width: 700px;
            margin: 0 auto;
        }

        /* Custom widths for Address and Password */

        /* Adjust gender layout */
        .gender-label {
            display: inline-block;
        }

        .gender-radios {
            display: block;
        }
    </style>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/signup.js?dummy=<%=n%>"></script>
    <script src="${pageContext.request.contextPath}/js/Javascript.js?dummy=<%=n%>"></script>
</head>
<body>
<%@ include file="../Include/Header.jsp" %>

<div class="container-fluid d-flex justify-content-center align-items-center">
    <div class="card p-4 m-2">
        <h2 class="text-center mb-4">Create an Account</h2>
        <form>
            <!-- Name, Email, and Mobile Number in the same line -->
            <div class="row">
                <div class="col-12 col-md-4 mb-3">
                    <label for="name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="name" placeholder="Enter your Name" />
                </div>
                <div class="col-12 col-md-4 mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="text" class="form-control" id="email" placeholder="Enter your email" />
                </div>
                <div class="col-12 col-md-4 mb-3">
                    <label for="phonenumber" class="form-label">Mobile Number</label>
                    <input type="text" class="form-control" id="phonenumber" placeholder="Enter your Mobile Number" />
                </div>
            </div>

            <!-- Gender and Country Dropdown in the same line -->
            <div class="row mb-3" >
                <div class="col-12 col-md-6" >
                    <label class="form-label gender-label">Gender</label>
                    <div style="display: flex">
                    <div class="form-check form-check-inline gender-radios" style="flex: 1;">
                        <input class="form-check-input" type="radio" name="Gender" id="Male" value = "1">
                        <label class="form-check-label" for="Male">Male</label>
                    </div>
                    <div class="form-check form-check-inline gender-radios" style="flex: 1">
                        <input class="form-check-input" type="radio" name="Gender" id="Female" value="0">
                        <label class="form-check-label" for="Female">Female</label>
                    </div>
                    </div>
                </div>
                <div class="col-12 col-md-6">
                    <label for="country" class="form-label">Country</label>
                    <select name="country" id="country" class="form-control">
                        <option value="">Select</option>
                        <option value="India">India</option>
                        <option value="Spain">Spain</option>
                        <option value="SouthAfrica">South Africa</option>
                    </select>
                </div>
            </div>

            <div class="mb-3">
                <label for="Address" class="form-label">Address</label>
                <textarea class="form-control" id="Address" placeholder="Address" rows="1" style="resize: none"></textarea>
            </div>

            <!-- Address and Password in the same line with 75% and 25% widths -->
            <div class="row mb-3">
                <div class="col-12 col-md-4">
                    <label for="username" class="form-label">Username</label>
                    <input type="text" class="form-control" id="username" placeholder="Enter your Username" />
                </div>
                <div class="col-12 col-md-4">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" placeholder="Enter your password" />
                </div>

                <div class="col-12 col-md-4">
                    <label for="confirmpassword" class="form-label">Confirm Password</label>
                    <input type="password" class="form-control" id="confirmpassword" placeholder="Confirm Password" />
                </div>
            </div>

            <!-- Submit Button -->
            <button type="button" class="btn btn-primary w-100" onclick="signUp()">SignUp</button>
        </form>
        <div class="mt-3 text-center">
            <p>Already have an account? <a href="/login">Login here</a></p>
        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>
</body>
</html>
