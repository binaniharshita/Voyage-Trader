<%@page import="com.danskeit.srs2.bean.ShipBean"%>
<%@page import="com.danskeit.srs2.*" %>
<%@page import="java.util.*" %>
<%ShipBean sb=(ShipBean)request.getAttribute("selectedShip"); %>
<!doctype html>
<html lang="en">
  <head>
  	<title>Sidebar 01</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" type="text/css" href="../css/table.css">
  </head>
  <body>
		
		<div class="wrapper d-flex align-items-stretch">
			<nav id="sidebar">
				<div class="p-4 pt-5">
		  	 <ul class="mb-5">
	           
                <li><a href="../adminhome">Admin Home</a></li>
	           </ul>

	      </div>
    	</nav>

        <!-- Page Content  -->
      <div id="content" class="p-4 p-md-5">

        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <h2>Welcome to Ship Reservation System</h2>
        </nav>
		<table id="t01">
			  <tr>
                  <th>Ship ID</th>
                  <th>Ship Name</th>
                  <th>Seating Capcacity</th>
                  <th>Reservation Capacity</th>
                
                
                <tr>
                  <td><%=sb.getShipid() %></td>
                  <td><%=sb.getShipname() %></td>
                  <td><%=sb.getSeatingcapacity() %></td>
                  <td><%=sb.getReservationcapacity() %></td>
                  </tr> 
             
             
            </table>
        

      </div>
    </div>
  </div>
		</div>

    <script src="js/jquery.min.js"></script>
    <script src="js/popper.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
  </body>
</html>