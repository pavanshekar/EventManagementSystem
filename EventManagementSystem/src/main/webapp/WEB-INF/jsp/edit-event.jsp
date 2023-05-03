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
			                    <a class="dropdown-item" href="update-profile.htm" style="margin-bottom: 5px;" data-bs-toggle="modal" role="button" onclick="event.preventDefault(); location.href='update-profile.htm';">
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
                            <h4>Edit Event</h4>
                        </div>
                        <div class="card-body">
                            <form:form method="POST" action="edit-event.htm" enctype="multipart/form-data" modelAttribute="event">
                                <form:input type="hidden" path="eventid" value="${event.eventid}"/>
                                <div class="form-group">
                                    <label for="name">Event Name</label>
                                    <form:input type="text" class="form-control" id="name" path="name" value="${event.name}" required="true"/>
                                    <form:errors path="name" class="text-danger"/>
                                </div>
                                <div class="form-group mt-3">
								    <label for="description">Event Description</label>
								    <form:textarea class="form-control" id="description" path="description" rows="3" required="true" value="${event.description}"/>
								    <form:errors path="description" class="text-danger"/>
								</div>
                                <div class="form-group mt-3">
                                    <label for="location">Event Location</label>
                                    <form:input type="text" class="form-control" id="location" path="location" value="${event.location}" required="true"/>
                                	<form:errors path="location" class="text-danger"/>
                                </div>
                                <div class="form-group mt-3">
                                    <label for="startDateTime">Start Date and Time</label>
                                    <form:input type="datetime-local" class="form-control" id="startDateTime" path="startDateTime" value="${event.startDateTime}" required="true"/>
                                	<form:errors path="startDateTime" class="text-danger"/>
                                </div>
                                <div class="form-group mt-3">
                                    <label for="endDateTime">End Date and Time</label>
                                    <form:input type="datetime-local" class="form-control" id="endDateTime" path="endDateTime" value="${event.endDateTime}" required="true"/>
                                	<form:errors path="endDateTime" class="text-danger"/>
                                </div>
                                <div class="form-group mt-3">
                                    <label for="numberOfTickets">Number of Tickets</label>
                                    <form:input type="number" class="form-control" id="numberOfTickets" path="numberOfTickets" value="${event.numberOfTickets}" required="true"/>
                                	<form:errors path="numberOfTickets" class="text-danger"/>
                                </div>
                                <div class="form-group mt-3">
                                    <label for="cost">Event Cost</label>
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text">$</span>
                                        </div>
                                        <form:input type="number" class="form-control" id="cost" path="cost" step="0.01" min="0" value="${event.cost}" required="true"/>
                                    	<form:errors path="cost" class="text-danger"/>
                                    </div>
                                </div>
                                <div class="form-group mt-3">
                                    <label for="image">Event Image</label>
                                    <input type="file" class="form-control" id="image" name="image" required="true">
                                </div>
                                <div class="form-group mt-3 text-center">
                                    <button type="submit" class="btn btn-primary btn-block">Update Event</button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>