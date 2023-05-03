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
            <a class="navbar-brand" href="index.htm">Event Management System</a>
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
                <div class="col-md-6">
                    <div class="card mt-5">
                        <div class="card-header text-center">
                            <h4>Login</h4>
                        </div>
                        <div class="card-body">
                            <form:form method="post" action="login.htm" modelAttribute="user">
		                        <div class="form-group">
		                           <label for="email">Email</label>
		                           <form:input path="email" type="email" class="form-control" id="email"/>
		                           <form:errors path="email" class="text-danger" />
		                        </div>
		                        <div class="form-group mt-3">
		                           <label for="password">Password</label>
		                           <form:input path="password" type="password" class="form-control" id="password" />
		                           <form:errors path="password" class="text-danger" />
		                        </div>
		                        <div class="form-group mt-3 text-center">
		                           <button type="submit" class="btn btn-primary btn-block">Login</button>
		                           <c:if test="${not empty error}">
									    <div class="text-danger">${error}</div>
								   </c:if>

		                        </div>
		                     </form:form>
                            <div class="mt-3 text-center">
                                <span>New user?</span>
                                <a href="register.htm">Register here</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>