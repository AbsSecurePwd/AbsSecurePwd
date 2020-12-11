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
 
<title>Contact Form</title>
</head>
<body background="Selling-Scrap-Online.png">
<style>
    .h1{
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

.h2
{
    align-items: center;
}
.addressMain {
}
.addressSec {
}
.txt{
    padding:15px;
    

</style>
<div style="border:1px solid #ccc; width: 50%; margin:0 auto; background-image: url('about1.png');">
    <h2 style="align-content: center"> CONTACT US</h2>
  <div class="container">
    
    <form action="contact" method="post">
    <label for="fname">First Name</label>
    <input type="text" id="fname" name="fname" placeholder="Your name..">

    <label for="lname">Last Name</label>
    <input type="text" id="lname" name="lname" placeholder="Your last name..">

    <label for="city">City</label>
    <input type="text" id="lname" name="city" placeholder="City">
    <label for="subject">Subject</label>
    <div class="txt">
    <textarea  id="subject" name="subject" placeholder="Write something.."></textarea>
    </div>
    <input type="submit" value="Submit">
  </form>
</div>

</body>
</html>
