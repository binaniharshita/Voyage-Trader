<%@page import="com.danskeit.srs2.bean.CredentialsBean"%>
<%@page import="com.danskeit.srs2.*" %>
<%@page import="java.util.*" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

  
<link rel="stylesheet" href="css/sidebar_form.css">
<script src="js/jquery.min.js"></script>
    <script src="js/popper.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
</head>
<body onload="openNav()">

<div class="sidenav" id="mySidenav">
	<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  
  <a href="adminhome">Home</a>
  <button class="dropdown-btn4">User
    <i class="fa fa-caret-down"></i>
  </button>
  <div class="dropdown-container4">
    <a href="changePass">Change Password</a>
    <a href="logout">Logout</a>
  </div>
  <button class="dropdown-btn1">Ships
    <i class="fa fa-caret-down"></i>
  </button>
  <div class="dropdown-container1">
    <a href="admin/addship">Add Ship</a>
    <a href="admin/viewallship">View Ships</a>
    <a href="admin/modifyship">Modify Ships</a>
    <a href="admin/deleteship">Delete Ships</a>
  </div>
   <button class="dropdown-btn2">Routes
    <i class="fa fa-caret-down"></i>
  </button>
  <div class="dropdown-container2">
    <a href="admin/addroute">Add Route </a>
    <a href="admin/viewallroute">View Routes</a>
    <a href="admin/modifyRoute">Modify Route</a>
    <a href="admin/deleteRoute">Delete Route</a>
  </div>
  <button class="dropdown-btn3">Schedules
    <i class="fa fa-caret-down"></i>
  </button>
  <div class="dropdown-container3">
    <a href="admin/addschedule">Add Schedule</a>
    <a href="admin/viewallschedule">View Schedules</a>
    <a href="admin/modifyschedule">Modify Schedules</a>
    <a href="admin/deleteSchedule">Delete Schedule</a>
  </div>
 <a href="admin/viewReservationDetails">View Reservation Details</a>
  <a href="admin/ViewPass">View Passenger Details</a>
</div>

<div id="main">
  <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776;</span>
  <h1><div class='wood-text'>VOYAGE TRADER</div></h1>
  <h2></h2>
  <div class="container-login100">
            <div class="card card-4">
                <div class="card-body">
                    <h2 class="title"><%String id=(String)session.getAttribute("user");%></h2>
                    <form action="changePassSubmit">
                        <div class="input-group">
                            <label class="label">Enter new Password</label>
                            <input class="input--style-4" type="password" name="newPass" id="password">
                        </div>
                         <div class="input-group">
                            <label class="label">Re-enter New Password</label>
                            <input class="input--style-4" type="password" name="newPass1" id="confirm_password">
                        </div>
                        <span id='message'></span>
                        
                        <div class="p-t-15">
                            <button class="btn btn--radius-2 btn--blue" type="submit">Submit</button>
                        </div>

                       

                    </form>
                </div>
            </div>
        </div>
      </div>
		</div>

    <script src="js/jquery.min.js"></script>
    <script src="js/popper.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
     <script>
  $('#password, #confirm_password').on('keyup', function () { 
      if ($('#password').val() == $('#confirm_password').val()) {   
            $('#message').html('').css('color', 'green');  
       }
        else 
        $('#message').html('Both the entries should match!').css('color', 'red'); });
</script>
<script>
/* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
var dropdown1 = document.getElementsByClassName("dropdown-btn1");
var i;

for (i = 0; i < dropdown1.length; i++) {
  dropdown1[i].addEventListener("click", function() {
  this.classList.toggle("active");
  var dropdownContent = this.nextElementSibling;
  if (dropdownContent.style.display === "block") {
  dropdownContent.style.display = "none";
  } else {
  dropdownContent.style.display = "block";
  }
  });
}

var dropdown2 = document.getElementsByClassName("dropdown-btn2");
//var i;

for (i = 0; i < dropdown2.length; i++) {
  dropdown2[i].addEventListener("click", function() {
  this.classList.toggle("active");
  var dropdownContent = this.nextElementSibling;
  if (dropdownContent.style.display === "block") {
  dropdownContent.style.display = "none";
  } else {
  dropdownContent.style.display = "block";
  }
  });
}


var dropdown3 = document.getElementsByClassName("dropdown-btn3");
//var i;

for (i = 0; i < dropdown3.length; i++) {
  dropdown3[i].addEventListener("click", function() {
  this.classList.toggle("active");
  var dropdownContent = this.nextElementSibling;
  if (dropdownContent.style.display === "block") {
  dropdownContent.style.display = "none";
  } else {
  dropdownContent.style.display = "block";
  }
  });
}

var dropdown4 = document.getElementsByClassName("dropdown-btn4");
//var i;

for (i = 0; i < dropdown4.length; i++) {
  dropdown4[i].addEventListener("click", function() {
  this.classList.toggle("active");
  var dropdownContent = this.nextElementSibling;
  if (dropdownContent.style.display === "block") {
  dropdownContent.style.display = "none";
  } else {
  dropdownContent.style.display = "block";
  }
  });
}


function openNav() {
  document.getElementById("mySidenav").style.width = "300px";
  document.getElementById("main").style.marginLeft = "300px";
}

function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
  document.getElementById("main").style.marginLeft= "0";
}
</script>

</body>
</html> 