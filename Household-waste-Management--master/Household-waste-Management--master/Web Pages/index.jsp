<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title>LOGIN HERE</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
<div class="header">
    <a href="#default" class="logo">Trash Collector</a>
<div class="header-right">
      
    <a class="active"href="#">HOME</a>
   <a href="price.jsp">PRICE LIST</a>
    <a href="index.jsp">REQUEST PICKUP</a>
    <a href="signup.jsp">SIGN UP</a>
    

</div>
</div>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<div class="icon-bar">
  <a href="https://www.facebook.com/" class="facebook"><i class="fa fa-facebook"></i></a> 
  <a href="https://www.twitter.com/" class="twitter"><i class="fa fa-twitter"></i></a> 
  <a href="https://www.google.com/" class="google"><i class="fa fa-google"></i></a> 
  <a href="https://www.linkedin.com/" class="linkedin"><i class="fa fa-linkedin"></i></a>
  <a href="https://www.youtube.com/" class="youtube"><i class="fa fa-youtube"></i></a> 
</div>
<meta charset="UTF-8">
  <title>Sell Scrap with Trash Collector</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
</head>
<style>
.footer
{
  overflow: hidden;
  position: fixed;
   left: 0;
   bottom: 0;
   width: 100%;
   background-color:limegreen;
   color: black;
   text-align: center;
   padding: 20px 10px;
   font-weight: bold;
   background-image: url("about1.png");
}

.header 
{
  overflow: hidden;
  
  padding: 20px 10px;
  background-image: url("about1.png");
}

.header a {
  float: left;
  color: black;
  text-align: center;
  padding: 12px;
  text-decoration: none;
  font-size: 18px; 
  line-height: 25px;
  border-radius: 4px;
}

/* Style the logo link (notice that we set the same value of line-height and font-size to prevent the header to increase when the font gets bigger */
.header a.logo {
  font-size: 25px;
  font-weight: bold;
  background-image:url("logo.jpg");
  
  
}

/* Change the background color on mouse-over */
.header a:hover {
  background-color:white;
  color: black;
}

/* Style the active/current link*/
.header a.active {
  background-color:green;
  color: white;
}

/* Float the link section to the right */
.header-right {
  float: right;
}

/* Add media queries for responsiveness*/ 
@media screen and (max-width: 500px) 
{
  .header a 
  {
    float: none;
    display: block;
    text-align: left;
  }
  .header-right 
  {
    float: none;
  }
}
body, html 
{
  box-sizing: border-box;
  height: 100%;
  width: 100%;
  margin: 10;
  }
.bg {
    /* The image used */
    

    /* Full height */
    height: 100%; 
    width: 100%;

    /* Center and scale the image nicely */
    background-position:right;
    background-repeat: no-repeat;
    background-size:cover;
}
.icon-bar {
  position: fixed;
  top: 50%;
  -webkit-transform: translateY(-50%);
  -ms-transform: translateY(-50%);
  transform: translateY(-50%);
}

.icon-bar a {
  display: block;
  text-align: center;
  padding: 16px;
  transition: all 0.3s ease;
  color: white;
  font-size: 20px;
}

.icon-bar a:hover {
    background-color: #000;
}

.facebook {
  background: #3B5998;
  color: white;
}

.twitter {
  background: #55ACEE;
  color: white;
}

.google {
  background: #dd4b39;
  color: white;
}

.linkedin {
  background: #007bb5;
  color: white;
}

.youtube {
  background: #bb0000;
  color: white;
}

@import url("https://fonts.googleapis.com/css?family=Dancing+Script|Lato");
:root{
    --primaryred:#ff424f;
    --secondaryred:#f84735;
    --lightblack:#222b31;
    --darkblack:#191c1f;
    --mainfont:'Dancing Script', cursive;
    --secondfont: 'Lato', sans-serif;
}
body{
	margin: 0;
	padding: 0;
	font-size: 16px;
	line-height: 1.5;
	font-family:var(--secondfont);
}
*,*:before,*:after{
	box-sizing: border-box;
}
img{
	max-width: 100%;
	height: auto;
}
.fullcontent{
    width:100%;
    min-height: 100vh;
    height: auto;
    padding:6% 10% 5% 10%;
    background-image: url("index1.jpg");
    background:linear-gradient(rgba(0, 0, 0, 0.70), rgba(0, 0, 0, 0)),url("Selling-Scrap-Online.png");
    background-size: cover;
    background-position: 50% 50%;
    repeat:no-repeat;
    display: flex;
    flex-direction: row;
    opacity: 50;
}
.headings{
     /* background:red;  */
    flex-basis: 65%;
    margin-right: 5%;
}
.headings h1{
    font-size: 4rem;
    font-family:var(--mainfont);
    color:white;
    margin:3% 0% 3% 0%;
}
.headings h2{
	font-size:25px;
    font-weight: bold;
    color: lime;
}
.formbox{
    background:rgb(34,43,49,0.7);
    color:limegreen;
    flex-basis: 35%;
    border-top: 10px solid var(--secondaryred);
    padding:2% 3%;
}
.formbox *{
    margin: 0;
}
.formbox h3{
    font-family: var(--mainfont);
    font-size: 1.5rem;
    margin-bottom: 1rem;
}
.formbox form label{
    color: rgba(255, 255, 255, 0.8);
    display: inline-block;
    max-width: 100%;
    margin-bottom: 0.3rem;
}
.formbox form .asd{
    border: 2px solid rgba(255, 255, 255, 0.2);
    box-shadow: none;
    background:transparent;
    height: 2.8rem;
    display:block;
    width: 100%;
    border-radius: 4px;
    margin-bottom: 1rem;
    color: darkorange;
}
form .mainbox{
    color:limegreen;
    box-shadow: none;
    background: var(--secondaryred);
    height: 2.8rem;
    display: block;
    width: 100%;
    border-radius: 4px;
    margin-bottom: 10px;
    text-align: center;
    text-decoration: none;
    border:white;
}
a .mainbox{
    margin: 0;
    padding: 2%;
}

</style>
</head>
<div class="footer">
    <p>&copy; 2018  Trash Collector. All Rights Reserved </p>
    <p><a href="contact.jsp">Terms &Conditions</a>|<a href="contact.jsp">Contact Us</a>|<a href="contact.jsp">Privacy Policy </a></p>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.3/modernizr.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">   
</head>

  <div class="fullcontent">
    <div class="headings">
        <h1>TRASH COLLECTOR</h1>
        <h2>
        	Welcome User!!!
        	This is a smart solution for all your waste problems,which saves time,energy and the environment.You can book your pickup request through the web.
        </h2>
    </div>
    <div class="formbox">
        <h3>Log In</h3>
        <form id="form" action="index" method="post">
            <label for="">Username</label>
            <input type="text" name="username" class="asd" required/>
            <label for="">Password</label>
            <input type="password" id="" name="password" class="asd" required/>
            <input type="submit" name="submit" value="SELL" class="mainbox">
            <label for="">New customer?</label>
            <div>
                <a href="signup.jsp"><input type="button" class="mainbox" value="REGISTER" ></a>
            </div>
            <br>
            
            <div>
                <a href="pickup.jsp"><input type="button" name="donate" value="DONATE" class="mainbox"></a></div>
                <div><br>
                <!--input type="button" name="donate" value="SELL" class="mainbox"></div-->
            
            <!--div>
                <a href="signup.jsp"><input type="button" class="mainbox" value="REGISTER" ></a>
            </div-->
        </form>
    </div>
</div>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js'></script>
</body>
</html>