<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="css/sidebar_form.css">
<!--<link href="css/main.css" rel="stylesheet" media="all">-->
<script src="js/jquery.min.js"></script>
    <script src="js/popper.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/main.js"></script>
</head>
<body style="background-image: url('images/download.png');">


  
<div id="main" >
  <h1><div class='wood-text'>VOYAGE TRADER</div></h1>
  <h2></h2>
  <!--<p>Click on the element below to open the side navigation menu, and push this content to the right.</p>-->
     <div class="container-login100" >
            <div class="wrap-login100">
            <div class="card card-4">
                <div class="card-body">
                    <h2 class="title">Login</h2>
                    <form method="POST" action="login">
                        <div class="input-group">
                            <label class="label">UserId</label>
                            <input class="input--style-4" type="text" name="userid" required>
                        </div>
                        <div class="input-group">
                            <label class="label">Password</label>
                            <input class="input--style-4" type="password" name="password" required>
                        </div>
                        <div class="p-t-15">
                            <button class="btn btn--radius-2 btn--blue" type="submit">Login</button>
                        </div>

                        <div class="text-center p-t-90">
                            <a class="label" href="register">
                                New Customer? Register here!
                            </a>
                        </div>

                    </form>
                </div>
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
