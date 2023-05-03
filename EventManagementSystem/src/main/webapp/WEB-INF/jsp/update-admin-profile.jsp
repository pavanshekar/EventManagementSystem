<%@page import="com.webtools.eventmanagement.pojo.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Event Management</title>
        <script src="https://code.jquery.com/jquery-3.6.1.min.js"
        integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
        <style>
            .container {
                margin-top: 50px;
            }
            #signoutButton {
                margin-left: auto;
            }
            #menu {
            	left: auto;
            	right: 0;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <a class="navbar-brand" href="admin.htm">Event Management System</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-start" id="navbarNav">
                <ul class="navbar-nav">
                	<li class="nav-item">
                        <a class="nav-link" href="admin-users.htm">Users</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="admin-events.htm">Events</a>
                    </li>
                </ul>
                <ul class="navbar-nav" id="signoutButton">
                    <li class="nav-item dropdown">
		                <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">
		                    <i class="fa fa-user"></i>
		                </a>
		                <ul class="dropdown-menu" id="menu">
		                    <li class="nav-item">
			                    <a class="dropdown-item" href="update-admin-profile.htm" style="margin-bottom: 5px;" data-bs-toggle="modal" role="button" onclick="event.preventDefault(); location.href='update-admin-profile.htm';">
			                    	Update Profile
			                    </a>
		                    </li>
		                    <li><hr class="dropdown-divider"></li>
		                    <li>
			                    <a class="dropdown-item" href="signout.htm" data-bs-toggle="modal" role="button" onclick="event.preventDefault(); location.href='signout.htm';">
			                    	Sign Out
			                    </a>
		                    </li>
		                </ul>
            		</li>
                </ul>
            </div>
        </nav>
        
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card mt-5">
                        <div class="card-header text-center">
                            <h4>Update Admin Profile</h4>
                        </div>
                        <div class="card-body">
                            <form:form method="POST" action="update-admin-profile.htm" modelAttribute="user">
                                <form:input type="hidden" path="userid" value="${user.userid}"/>
                                <div class="form-group">
                                    <label for="firstName">First Name</label>
                                    <form:input path="firstName" type="text" class="form-control" id="firstName" value="${user.firstName}" />
								    <form:errors path="firstName" class="text-danger" />
                                    
                                </div>
                                <div class="form-group mt-3">
                                    <label for="lastName">Last Name</label>
                                    <form:input path="lastName" type="text" class="form-control" id="lastName" value="${user.lastName}" />
								    <form:errors path="lastName" class="text-danger" />
                                    
                                </div>
                                <div class="form-group mt-3">
                                    <label for="email">Email</label>
                                    <form:input path="email" type="email" class="form-control" id="email" value="${user.email}" />
								    <form:errors path="email" class="text-danger" />
                                    
                                </div>
                                <div class="form-group mt-3">
                                    <label for="password">Password</label>
                                    <form:input path="password" type="password" class="form-control" id="password" value="${user.password}" />
								    <form:errors path="password" class="text-danger" />
                                    
                                </div>
                                <div class="form-group mt-3">
                                    <label for="phone">Phone</label>
                                    <form:input path="phone" type="tel" class="form-control" id="phone" value="${user.phone}" />
								    <form:errors path="phone" class="text-danger" />
                                    
                                </div>
                                
                                <form:input type="hidden" path="address.addrid" value="${address.addrid}"/>
                                <div class="form-group mt-3">
                                    <label for="streetNum">Street Number</label>
                                    <form:input path="address.streetNum" type="number" class="form-control" id="streetNum" value="${address.streetNum}" />
								    <form:errors path="address.streetNum" class="text-danger" />
                                    
                                </div>
                                <div class="form-group mt-3">
                                    <label for="streetName">Street Name</label>
                                    <form:input path="address.streetName" type="text" class="form-control" id="streetName" value="${address.streetName}" />
								    <form:errors path="address.streetName" class="text-danger" />
                                    
                                </div>
                                <div class="form-group mt-3">
                                    <label for="city">City</label>
                                    <form:input path="address.city" type="text" class="form-control" id="city" value="${address.city}" />
								    <form:errors path="address.city" class="text-danger" />
                                    
                                </div>
                                <div class="form-group mt-3">
                                    <label for="state">State</label>
                                    <form:input path="address.state" type="text" class="form-control" id="state" value="${address.state}" />
								    <form:errors path="address.state" class="text-danger" />
                                    
                                </div>
                                <div class="form-group mt-3">
                                    <label for="zip">Zip</label>
                                    <form:input path="address.zip" type="number" class="form-control" id="zip" value="${address.zip}" />
								    <form:errors path="address.zip" class="text-danger" />
                                    
                                </div>
                                <div class="form-group mt-3">
                                    <label for="country">Country</label>
                                    <form:input path="address.country" type="text" class="form-control" id="country" value="${address.country}" />
								    <form:errors path="address.country" class="text-danger" />
                                   
                                </div>
                                <div class="form-group mt-3 text-center">
                                    <button type="submit" class="btn btn-primary btn-block">Update Profile</button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>