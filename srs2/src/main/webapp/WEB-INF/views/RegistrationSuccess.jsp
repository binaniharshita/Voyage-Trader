<%@page import="com.danskeit.srs2.bean.CredentialsBean"%>
<%@page import="com.danskeit.srs2.*" %>
<%@page import="java.util.*" %>
<%String id=(String)request.getAttribute("id");%>
<!DOCTYPE html>
<html lang="en">

<head>
   
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">
    <title>Ship Reservation System</title>
    <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    
    <link href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

   
    <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

   
    <link href="css/main.css" rel="stylesheet" media="all">
</head>

<body>
        <div class="container-login100" style="background-image: url('images/ship.jpg');">
            <div class="wrap-login100">
            <div class="card card-4">
                <div class="card-body">
                    <h2 class="title">Registration Success!<br>
                    User ID:<%=id%></h2>
                    
                        

                        <div class="text-center p-t-90">
                            <a class="label" href="welcome">
                                Login 
                            </a>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>

    

</body>

</html>
