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
            .card-img-top {
                height: 200px; /* set a fixed height for the image container */
                object-fit: cover; /* scale the image to cover the container while preserving its aspect ratio */
            }

            .card-body {
                height: 350px;
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
            <a class="navbar-brand" href="events.htm">Event Management System</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-start" id="navbarNav">
                <ul class="navbar-nav">
                <li class="nav-item">
                        <a class="nav-link" href="my-events.htm">My Events</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="create-event.htm">Create Event</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Organized Events</a>
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
            <h1><center>Organized Events</center></h1>
            <div class="row">
                <c:forEach var="event" items="${organizedEvents}">
                    <div class="col-md-4">
                        <div class="card mb-4 box-shadow h-100">
                            <img class="card-img-top" src="data:image/png;base64,${event.base64Image}" alt="${event.name}">
                            <div class="card-body">
                                <h5 class="card-title text-center">${event.name}</h5>
                                <p class="card-text">${event.description}</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <small class="text-muted">${event.startDateTime}</small>
                                    <span class="badge bg-success">$${event.cost}</span>
                                </div>
                                <p class="card-text mt-3">
                        			<span class="${event.numberOfTickets > 0 ? 'text-success' : 'text-danger'}">${event.numberOfTickets > 0 ? 'Tickets available' : 'Tickets Sold Out'}</span>
                        		</p>
                                <div class="mt-4">
                                    <a href="<c:url value='/update-event'><c:param name='eventid' value='${event.eventid}'/></c:url>" class="btn btn-primary">
                                    	Update
                                    </a>
                                    <a href="delete-event?id=${event.eventid}">
                                    	<button class="btn btn-sm btn-danger float-end">Delete</button>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <c:if test="${(status.index + 1) % 3 == 0}">
                    </div><div class="row">
                    </c:if>
                </c:forEach>
            </div>
        </div>

    </body>
</html>