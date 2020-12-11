<%-- 
    Document   : pickup
    Created on : 5 Sep, 2018, 9:54:12 PM
    Author     : Kanishka
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 
<title>Pickup Request</title>
</head>
<body background="Selling-Scrap-Online.png">
<style>
    h1{
        align: center;
    }
* {box-sizing: border-box}

/* Full-width input fields */
  input {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  display: inline-block;
  border: none;
  background: #f1f1f1;
}

* {
	font-family: sans-serif;
}

input[type=text]:focus, input[type=password]:focus {
  background-color: #ddd;
  outline: none;
}

hr {
  border: 1px solid #f1f1f1;
  margin-bottom: 25px;
}

/* Set a style for all buttons */
button {
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  border-radius: 2px;
  opacity: 0.9;
}

button:hover {
  opacity:1;
}

/* Extra styles for the cancel button */
.cancelbtn {
  padding: 14px 20px;
  background-color: #f44336;
}

/* Float cancel and signup buttons and add an equal width */
.cancelbtn .signupbtn {
  float: left;
}

/* Add padding to container elements */
.container {
  padding: 16px;
}

/* Clear floats */
.clearfix::after {
  content: "";
  clear: both;
  display: table;
}

/* Change styles for cancel button and signup button on extra small screens */


.addressMain {
}
.addressSec {
}
</style>
<div style="border:1px solid #ccc; width: 50%; margin:0 auto; background-image: url('about1.png');">
    <form action="pickup" method="post">
  <div class="container">
    <h1>PICKUP REQUEST </h1>
    <p>Please fill in this form for Pickup Request.</p>
    <hr>
	<div class="addressSec">
		<label for="name"><b>Name</b></label>
    		<input type="text" placeholder="Enter Email" name="name" required>
	</div>
    
   <p> <strong> ADDRESS : </strong> </p>
   <div class = "addressMain">
    <div class = "addressSec">
    <label for="address_locality"><b>Locality</b></label>
    <input type="text" placeholder="Enter address" name="address" required>
    </div>
   <div class="addressSec">
    <label for="address_landmark"><b>Landmark</b></label>
    <input type="text" placeholder="Enter address" name="address1" required>
   
   </div> 
   <div class="addressSec">
    <label for="address_locality"><b>Other</b></label>
    <input type="text" placeholder="Enter address" name="address2" required>
   </div>
   </div>
     <div class = "addressMain">
    <div class = "addressSec">
        <p> <strong> PREFERABLE PICKUP DATE : </strong> </p>
     <label for="date"><b>Date</b></label>
     <input type="number" placeholder="Enter date" name="date" required>
    </div>
    <div class = "addressSec">
        <p> <strong> PREFERABLE PICKUP TIME: </strong> </p>
     <label for="time"><b>Time</b></label>
     <input type="number" placeholder="Enter time" name="time" required>
    
    </div>
    

    <div class="clearfix">
   
        <input type="button" class="cancelbtn" value="CANCEL"/>
      <input type="submit" class="signupbtn" value="CONFIRM"/>
    </div>
    </div>
  </div>
</form>
</div>

</body>
</html>