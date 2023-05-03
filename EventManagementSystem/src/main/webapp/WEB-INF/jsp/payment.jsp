<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        
        <div class="container mt-5">
			    <div class="card col-md-6 mx-auto">
			        <div class="card-header bg-primary text-white">
			            <h5 class="card-title mb-0">Payment Information</h5>
			        </div>
			        <div class="card-body">
			            <form:form action="payment.htm" method="post" class="mx-auto" modelAttribute="payment">
			                <div class="form-group row">
			                    <label for="cardHolderName" class="col-sm-3 offset-1">Cardholder Name</label>
			                    <div class="col-sm-7">
			                        <form:input type="text" class="form-control" id="cardHolderName" path="cardHolderName" placeholder="Enter Cardholder Name" required="true"/>
			                    	<form:errors path="cardHolderName" class="text-danger" />
			                    </div>
			                </div>
			                <div class="form-group row mt-3">
			                    <label for="cardNumber" class="col-sm-3 offset-1">Card Number</label>
			                    <div class="col-sm-7">
			                        <form:input type="text" class="form-control" id="cardNumber" path="cardNumber" placeholder="Enter Card Number" required="true"/>
			                    	<form:errors path="cardNumber" class="text-danger" />
			                    </div>
			                </div>
			                <div class="form-group row mt-3">
			                    <label for="expiryDate" class="col-sm-3 offset-1">Expiry Date</label>
			                    <div class="col-sm-2">
			                        <form:input type="text" class="form-control" id="expiryDate" path="expiryDate" placeholder="MM/YY" required="true"/>
			                    	<form:errors path="expiryDate" class="text-danger" />
			                    </div>
			                    <label for="cvv" class="col-sm-2 offset-1">CVV</label>
			                    <div class="col-sm-2">
			                        <form:input type="number" class="form-control" id="cvv" path="cvv" placeholder="CVV" required="true"/>
			                    	<form:errors path="cvv" class="text-danger" />
			                    </div>
			                </div>
			                <div class="form-group row mt-3">
			                    <label for="totalPrice" class="col-sm-3 offset-1">Total Price</label>
			                    <div class="col-sm-7">
			                        <form:input type="text" class="form-control" id="totalPrice" path="totalPrice" value="${sessionScope.totalPrice}" readonly="true"/>
			                    	<form:errors path="totalPrice" class="text-danger" />
			                    </div>
			                </div>
			                <div class="form-group row mt-3">
			                    <div class="col-sm-7 offset-sm-4">
			                        <button type="submit" class="btn btn-primary btn-block">Pay Now</button>
			                    </div>
			                </div>
			            </form:form>
			        </div>
			    </div>
			</div>    
   </body>
</html>