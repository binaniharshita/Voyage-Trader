<!doctype html>
<html lang="en">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

 <link rel="stylesheet" type="text/css" href="../css/table.css">
<link rel="stylesheet" href="../css/sidebar_form.css">
<script src="js/jquery.min.js"></script>
    <script src="js/popper.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
</head>
  <body  onload="openNav()">
		
<div class="sidenav" id="mySidenav">
	<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  
  <a href="../adminhome">Home</a>
  <button class="dropdown-btn4">User
    <i class="fa fa-caret-down"></i>
  </button>
  <div class="dropdown-container4">
    <a href="../changePass">Change Password</a>
    <a href="../logout">Logout</a>
  </div>
  <button class="dropdown-btn1">Ships
    <i class="fa fa-caret-down"></i>
  </button>
  <div class="dropdown-container1">
    <a href="addship">Add Ship</a>
    <a href="viewallship">View Ships</a>
    <a href="modifyship">Modify Ships</a>
    <a href="deleteship">Delete Ships</a>
  </div>
   <button class="dropdown-btn2">Routes
    <i class="fa fa-caret-down"></i>
  </button>
  <div class="dropdown-container2">
    <a href="addroute">Add Route </a>
    <a href="viewallroute">View Routes</a>
    <a href="modifyRoute">Modify Route</a>
    <a href="deleteRoute">Delete Route</a>
  </div>
  <button class="dropdown-btn3">Schedules
    <i class="fa fa-caret-down"></i>
  </button>
  <div class="dropdown-container3">
    <a href="addschedule">Add Schedule</a>
    <a href="viewallschedule">View Schedules</a>
    <a href="modifyschedule">Modify Schedules</a>
    <a href="deleteSchedule">Delete Schedule</a>
  </div>
 <a href="viewReservationDetails">View Reservation Details</a>
  <a href="ViewPass">View Passenger Details</a>
</div>
        <!-- Page Content  -->
<div id="main">
  <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776;</span>
  <h1><div class='wood-text'>VOYAGE TRADER</div></h1>
  <h2></h2>
        <div class="container-login100">
          <div class="card card-4">
                <div class="card-body">
                    <h2 class="title">Enter Ship Details</h2>

                    <form action="newShip">
                    
                        <div class="input-group">
                            <label class="label">Ship Name</label>
                            <input class="input--style-4" type="text" name="shipname" required>
                        </div>
                        <div class="input-group">
                            <label class="label">Seating Capacity</label>
                            <input class="input--style-4" type="text" name="seatingcapacity" required>
                        </div>
                        <div class="input-group">
                            <label class="label">Reservation Capacity</label>
                            <input class="input--style-4" type="text" name="reservationcapacity" required>
                        </div>
                        <div class="p-t-15">
                            <button class="btn btn--radius-2 btn--blue" type="submit">Submit</button>
                        </div>

                       

                    </form>
                </div>
            </div>
        
</div>

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
