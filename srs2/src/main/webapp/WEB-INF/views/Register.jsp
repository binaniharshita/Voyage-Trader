<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="stylesheet" href="css/sidebar_form.css">
<link href="css/main.css" rel="stylesheet" media="all">
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
       <div class="container-login100" style="background-image: url('images/ship.jpg');">
            <div class="wrap-login100">
            <div class="card card-4">
                <div class="card-body">
                    <h2 class="title">Registration Form</h2>
                    <form method="POST" action="newRegister">
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">First Name</label>
                                    <input class="input--style-4" type="text" name="firstname" required>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Last Name</label>
                                    <input class="input--style-4" type="text" name="lastname" required>
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Date of Birth</label>
                                    <div class="input-group-icon">
                                        <input class="input--style-4 js-datepicker" type="date" name="dateofbirth" required>
                                     
                                    </div>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group" >
                                    <label class="label">Gender</label>
                                    <div class="p-t-10">
                                        <label class="radio-container m-r-45">Male
                                            <input type="radio" checked="checked" name="gender" value="Male">
                                            <span class="checkmark"></span>
                                        </label>
                                        <label class="radio-container">Female
                                            <input type="radio" name="gender" value="Female">
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Location</label>
                                    <input class="input--style-4" type="text" name="location" required>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Street</label>
                                    <input class="input--style-4" type="text" name="street" required>
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">City</label>
                                    <input class="input--style-4" type="text" name="city" required>
                                </div>
                            </div>
                           
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Pincode</label>
                                    <input class="input--style-4" type="text" name="pincode" required>
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                             <div class="col-2">
                                <div class="input-group">
                                    <label class="label">State</label>
                                    <input class="input--style-4" type="text" name="state" required>
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Email</label>
                                    <input class="input--style-4" type="email" name="emailid" required>
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Mobile Number</label>
                                    <input class="input--style-4" type="text" name="mobileno" required>
                                </div>
                            </div>
                        </div>
                        <div class="input-group">
                            <label class="label">Password</label>
                            <input class="input--style-4" type="password" name="password" required>
                        </div>
                        <div class="p-t-15">
                            <button class="btn btn--radius-2 btn--blue" type="submit">Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

</body>
</html>