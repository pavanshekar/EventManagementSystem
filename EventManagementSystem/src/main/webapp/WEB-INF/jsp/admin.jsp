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
            
        </style>
    </head>
    <body>

        <nav class="navbar navbar-expand-md navbar-dark bg-dark">
            <a class="navbar-brand" href="#">Event Management System</a>
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
			  <h1 class="mb-3"><center>Welcome Admin</center></h1>
			  <div class="row">
			    <div class="col-md-4">
			      <div class="card">
			        <div class="card-header">Total Users</div>
			        <div class="card-body">
			          <h5 class="card-title">${totalUsers}</h5>
			          <p class="card-text">This is the total number of registered users in the system.</p>
			        </div>
			      </div>
			    </div>
			    <div class="col-md-4">
			      <div class="card">
			        <div class="card-header">Total Events</div>
			        <div class="card-body">
			          <h5 class="card-title">${totalEvents}</h5>
			          <p class="card-text">This is the total number of events created in the system.</p>
			        </div>
			      </div>
			    </div>
			    <div class="col-md-4">
			      <div class="card">
			        <div class="card-header">Total Revenue</div>
			        <div class="card-body">
			          <h5 class="card-title">$${totalRevenue}</h5>
			          <p class="card-text">This is the total revenue generated from all the events.</p>
			        </div>
			      </div>
			    </div>
			  </div>
		</div>
	</body>
</html>