<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        
        <div class="container mt-5">
        <h1 class="mb-5">Users</h1>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Street Name</th>
                    <th>Street Number</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Zip</th>
                    <th>Country</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${users}">
                	<c:if test="${sessionScope.user.email ne user.email}">
	                    <tr>
	                        <td>${user.firstName}</td>
	                        <td>${user.lastName}</td>
	                        <td>${user.email}</td>
	                        <td>${user.phone}</td>
	                        <td>${user.address.streetName}</td>
	                        <td>${user.address.streetNum}</td>
	                        <td>${user.address.city}</td>
	                        <td>${user.address.state}</td>
	                        <td>${user.address.zip}</td>
	                        <td>${user.address.country}</td>
	                        <td>
	                            <div class="btn-group">
								    <a href="<c:url value='/edit-user'><c:param name='userid' value='${user.userid}'/></c:url>" class="btn btn-primary">Update</a>
								    <a href="<c:url value='/delete-user'><c:param name='userid' value='${user.userid}'/></c:url>" class="btn btn-danger" onclick="return confirm('Are you sure you want to delete this user?')">Delete</a>
								</div>
	                        </td>
	                    </tr>
                    </c:if>
                </c:forEach>
            </tbody>
        </table>
    </div>
        
   </body>
</html>