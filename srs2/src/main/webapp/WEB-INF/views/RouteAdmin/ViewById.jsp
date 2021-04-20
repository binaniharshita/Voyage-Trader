<%@page import="com.danskeit.srs2.bean.RouteBean"%>
<%@page import="com.danskeit.srs2.*" %>
<%@page import="java.util.*" %>
<%ArrayList<RouteBean> al=(ArrayList<RouteBean>)session.getAttribute("aab"); %>
<!doctype html>
<html lang="en">
  <head>
  	<title>Sidebar 01</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
		
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" type="text/css" href="../css/view.css">
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
<div class="limiter">
    <div class="container-table100">
      <div class="wrap-table100">
        <div class="table100 ver1 m-b-110">
          <div class="table100-head">
            <table>
              <thead>
                <tr class="row100 head">
                  <th class="cell100 column1">Route ID</th>
                  <th class="cell100 column2">Source</th>
                  <th class="cell100 column3">Destination</th>
                  <th class="cell100 column4">Travel Duration</th>
                   <th class="cell100 column5">Fare</th>
                  </tr>
              </thead>
            </table>
          </div>

          <div class="table100-body js-pscroll">
            <table>
              <tbody>
                <%for(RouteBean rb:al)
                { %>
                <tr class="row100 body">
                  <td class="cell100 column1"><%=rb.getRouteid() %></td>
                  <td class="cell100 column2"><%=rb.getSource() %></td>
                  <td class="cell100 column3"><%=rb.getDestination()  %></td>
                  <td class="cell100 column4"><td><%=rb.getTravelduration() %></td>
                  <td class="cell100 column5"><td><%=rb.getFare() %></td>
                  </tr> 
              <%} %>
              </tbody>
            </table>
          </div>
        </div>
        

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