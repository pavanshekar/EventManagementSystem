<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Event Management</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <style>
            .container {
                margin-top: 50px;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <a class="navbar-brand" href="home.htm">Event Management System</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="login.htm">Login / SignUp</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card mt-5">
                        <div class="card-header text-center">
                            <h4>Register</h4>
                        </div>
                        <div class="card-body">
                            <form:form method="POST" action="register.htm" modelAttribute="user">    
	                                <div class="form-group">
								        <label for="firstName">First Name</label>
								        <form:input path="firstName" type="text" class="form-control" id="firstName" />
								        <form:errors path="firstName" class="text-danger" />
								    </div>
								    <div class="form-group mt-3">
								        <label for="lastName">Last Name</label>
								        <form:input path="lastName" type="text" class="form-control" id="lastName" />
								        <form:errors path="lastName" class="text-danger" />
								    </div>
								    <div class="form-group mt-3">
								        <label for="email">Email</label>
								        <form:input path="email" type="email" class="form-control" id="email" />
								        <form:errors path="email" class="text-danger" />
								    </div>
	                                <div class="form-group mt-3">
								        <label for="password">Password</label>
								        <form:input path="password" type="password" class="form-control" id="password" />
								        <form:errors path="password" class="text-danger" />
								    </div>
								    <div class="form-group mt-3">
								        <label for="phone">Phone</label>
								        <form:input path="phone" type="tel" class="form-control" id="phone" />
								        <form:errors path="phone" class="text-danger" />
								    </div>
								
								
								    <div class="form-group mt-3">
								        <label for="streetNum">Street Number</label>
								        <form:input path="address.streetNum" type="number" class="form-control" id="streetNum" />
								        <form:errors path="address.streetNum" class="text-danger" />
								    </div>
								    <div class="form-group mt-3">
								        <label for="streetName">Street Name</label>
								        <form:input path="address.streetName" type="text" class="form-control" id="streetName" />
								        <form:errors path="address.streetName" class="text-danger" />
								    </div>
								    <div class="form-group mt-3">
								        <label for="city">City</label>
								        <form:input path="address.city" type="text" class="form-control" id="city" />
								        <form:errors path="address.city" class="text-danger" />
								    </div>
								    <div class="form-group mt-3">
								        <label for="state">State</label>
								        <form:input path="address.state" type="text" class="form-control" id="state" />
								        <form:errors path="address.state" class="text-danger" />
								    </div>
								    <div class="form-group mt-3">
								        <label for="zip">Zip</label>
								        <form:input path="address.zip" type="number" class="form-control" id="zip" />
								        <form:errors path="address.zip" class="text-danger" />
								    </div>
								    <div class="form-group mt-3">
								        <label for="country">Country</label>
								        <form:input path="address.country" type="text" class="form-control" id="country" />
								        <form:errors path="address.country" class="text-danger" />
								    </div>
								
                                <div class="form-group mt-3 text-center">
                                    <button type="submit" class="btn btn-primary btn-block">Register</button>
                                    <c:if test="${not empty error}">
									    <div class="text-danger">${error}</div>
								   </c:if>
                                </div>
                            </form:form>
                            <div class="mt-3 text-center">
                                <span>Already registered?</span>
                                <a href="login.htm">Login here</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>