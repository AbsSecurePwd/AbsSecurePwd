<%-- 
    Document   : confirm
    Created on : 5 Sep, 2018, 11:33:17 PM
    Author     : Kanishka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title>Basics | Confirmation Page</title>
</head>
<body>
	<style>
		body {
  background-image: url("Selling-Scrap-Online.png");
  text-align: center;
  font-family: Arial, Helvetica, san-serif;
  font-size: 1em;
  margin: 2em;
}



.section {
  margin: 30px;
}

.footer {
  
  padding: 20px 0px 0px 0px;
  background-image:url("about1.png");
}

h1 {
  text-transform: capitalize;
  font-family: Times, "Times New Roman", serif;
  font-size: 2em;
}

p {
  padding-bottom: 30px;
}

a {
  text-decoration: none;
  color: #005c4e;
  font-weight: bold;
}

a:hover {
  text-decoration: underline;
}


/* Specific */

body div {
  background-color: #ffffff;
}

header img {
  width: 300px;
  padding-bottom: 1.5em;
  border-bottom: 2px solid #005C4E;
}

section img {
  width: 150px;
  float: right;
}

footer p {
  padding: 0px;
  margin: 0px;
  padding: 0px 0px 20px 0px;
  font-size: 14px;
  background-image: url("price.jpg");
}


/* Classes */

.center {
  min-width: 400px;
  max-width: 800px;
  width: 90%;
  margin: auto;
  background-image:url("about1.png");
}

.border {
  border: 2px solid #989898;
  background-image:url("about1.png");
  
}

.button {
  background: #005c4e;
  background-image: -webkit-linear-gradient(top, #005c4e, #02332b);
  background-image: -moz-linear-gradient(top, #005c4e, #02332b);
  background-image: -ms-linear-gradient(top, #005c4e, #02332b);
  background-image: -o-linear-gradient(top, #005c4e, #02332b);
  background-image: linear-gradient(to bottom, #005c4e, #02332b);
  -webkit-border-radius: 10;
  -moz-border-radius: 10;
  border-radius: 10px;
  font-family: Arial;
  color: #ffffff;
  font-size: 16px;
  padding: 12px 30px 12px 30px;
  text-decoration: none;
}

.button:hover 
{
  background: #005c4e;
  text-decoration: none;
}
.header 
{
  overflow: hidden;
  background-color:#b9f6ca;
  padding: 20px 10px;
  /*background-image: url("background.jpg");*/
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
	</style>

<meta charset="utf-8">
  <title>Simple Confirmation Landing Page</title>
  <meta name="description" content="Google">
  <meta name="author" content="ksibley">
</head>

<div class="header">
    <a href="#default" class="logo">Trash Collector</a>
<div class="header-right">
      
    <a class="active"href="#">HOME</a>
   <a href="price.jsp">PRICE LIST</a>
    <a href="index.jsp">REQUEST PICKUP</a>
    <a href="signup.jsp">SIGN UP</a>
    

</div>
</div>
  <!-- Containing div for all content -->
  <div class="center border">
    <!-- Logo -->
    <header>
        
        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQfTFj2Z4YXMvNFKU8j2VMrunz3_rMGERTpE0swt0GBbtJiHjhD">
    </header>
    <!-- Main Content Section -->
    <section>
      <h1>Thank you for your patience&nbsp;!!</h1>
      <p>Your address has been added to our Pickup list:</p>
      <p>And your tracking ID has been mailed to you.</p>
      
      <!--form action="confirm" method="post">
          <input class="button" type="number" text="track" placeholder="Your tracking ID"/>
          <br>
          <br-->
      <a href="track.jsp"><input class="button" type="button"value="TRACK"/></a>
      
      
    </section>
  </div>
    <!-- Footer -->
    <div class="footer">
                <p> &copy; <strong>2018  Trash Collector. All Rights Reserved </strong></p>
    <p><a href="contact.jsp">Terms &Conditions</a>|<a href="contact.jsp">Contact Us</a>|<a href="contact.jsp">Privacy Policy </a></p>
</div>
    
  
</body>

</html>