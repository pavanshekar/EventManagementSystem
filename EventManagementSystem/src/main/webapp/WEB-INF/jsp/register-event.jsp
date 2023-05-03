<%@taglib prefix="c" uri="jakarta.tags.core" %>

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
            .banner-image {
			    width: 100%;
			    height: 500px;
			    object-fit: cover;
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
                        <a class="nav-link" href="organized-events.htm">Organized Events</a>
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
        <!-- Banner Image -->
	        <div class="row">
	            <div class="col-md-12">
	                <img src="data:image/png;base64,${event.base64Image}" alt="Event Banner" class="img-fluid banner-image">
	            </div>
	        </div>
	        <!-- Event Name -->
	        <div class="row mt-3">
	            <div class="col-md-12">
	                <h2 class="text-center">${event.name}</h2>
	            </div>
	        </div>
	        <!-- Event Description -->
	        <div class="row mt-3">
	            <div class="col-md-12">
	                <p>${event.description}</p>
	            </div>
	        </div>
	        <!-- Event Time and Price -->
	        <div class="row mt-3">
	            <div class="col-md-6">
	                <p><strong>Date and Time:</strong> ${event.startDateTime} - ${event.endDateTime}</p>
	            </div>
	        </div>
	        <div class="row mt-3">
	            <div class="col-md-6">
	                <p><strong>Price:</strong> $${event.cost}</p>
	            </div>
	         </div>
	        <!-- Registration Form -->
	        <div class="row mt-3 justify-content-center">
		        <div class="col-md-6">
		            <div class="card mx-auto" style="max-width: 400px;">
		                <div class="card-body">
		                    <h4 class="card-title text-center">Register for the Event</h4>
		                    <form method="post" action="register-event.htm">
		                        <input type="hidden" name="eventId" value="${event.eventid}">
		                        <div class="form-group mt-3">
		                            <label for="numberOfTickets">Number of Tickets:</label>
		                            <input type="number" class="form-control" id="numberOfTickets" name="numberOfTickets" min="1" max="${event.numberOfTickets}" required>
		                        </div>
		                        <div class="form-group mt-3">
		                            <label for="totalPrice">Total Price:</label>
		                            <input type="text" class="form-control" id="totalPrice" name="totalPrice" value="$0" readonly>
		                        </div>
		                        <div class="text-center mt-3">
			                        <button type="submit" class="btn btn-sm btn-primary">Book Tickets</button>
			                    </div>
		                    </form>
		                </div>
		            </div>
		        </div>
	    	</div>
		</div>
    <script>
        $(document).ready(function() {
            // Calculate total price based on number of tickets
            $('#numberOfTickets').on('input', function() {
                var numberOfTickets = parseInt($(this).val());
                var pricePerTicket = ${event.cost};
                var totalPrice = numberOfTickets * pricePerTicket;
                $('#totalPrice').val('$' + totalPrice.toFixed(2));
            });
        });
    </script>
    </body>
</html>