<!DOCTYPE html>
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title>Register form</title>
  

  
</head>
<style>
@import url('https://fonts.googleapis.com/css?family=Roboto');
* {
  font-family: 'Roboto';
  letter-spacing: 0.05em;
  margin: 0;
  padding: 0;
}

html, body 
{
  text-align: center;
  height: 100%;
  background: #D09EA6; 
  background: -webkit-linear-gradient(left top, #00659C, #6A1B9A); 
  background: -o-linear-gradient(bottom right, #00659C, #6A1B9A); 
  background: -moz-linear-gradient(bottom right, #00695C, #6A1B9A); 
  background: linear-gradient(to bottom right, #00695C, #6A1B9A);
  overflow-x: hidden;
  background-image:url("Selling-Scrap-Online.png");
  background-size:cover;
}

.step-bar 
{
  text-transform: uppercase;
  color: white;
  font-size: 0.8em;
  margin-top: 40px;
}

.step-bar li
{
  display: inline-block;
  position: relative;
  margin: 0px 30px; 
  width: 120px;
}

.step-bar .number
{
  transition: background-color 0.1s linear, color 0.1s linear;
  display: inline-block;
  position: relative;
  color: #777;
  background-color: #fff; 
  width: 20px;
  padding: 8px 5px;
  margin-bottom: 4px;
  font-weight: 700;
  z-index: 1;
}

.step-bar .line 
{
  background-color: #fff;
  width: 180px;
  height: 5px;
  position: absolute;
  top: 15px;
  right: 60px;
  z-index: 0;
}

.step-bar .active {
  background-color: #6A1B9A;
  color: #fff;
}

.register-form 
{
  display: inline-block;
  background: #fff;
  padding: 20px 30px;
  width: 300px;
  text-align: center;
  -webkit-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.3);
  -moz-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.3);
  box-shadow: 0px 0px 15px 5px rgba(0,0,0,0.3);
  border-radius: 3px;
  position: relative;
}

.error 
{
  border: 1px solid #E57373;
  background-color: #FFEBEE;
}

.error-text 
{
  text-align: left;
  padding-left: 10px;
  font-size: 0.7em;
  color: #E57373;
}

h1 
{
  margin: 75px 0;
  display: block;
  color: purple;
  font-size: 1.15em;
  text-transform: uppercase;
  margin-bottom: 20px;
  font-weight: 200;
}

.register-form h2 
{
  font-size: 1em;
  color: #777;
  font-weight: 400;
  margin-bottom: 10px;
}

.register-form input
{
  padding: 15px; 
  margin: 5px 0;
  width: 260px;
}

.register-form input::placeholder 
{
  color: #aaa;
}

.register-form input.error::placeholder 
{
  color: #555;
}

.button 
{
  display: inline-block;
  padding: 10px 40px;
  background-color: #6A1B9A;
  color: #fff;
  margin: 25px 0  10px 0;
  user-select: none;
  cursor: pointer;
  position: relative;
}

.button:hover 
{
  background-color: #00897B;
}
.account-setup, .finish-step, .user-details 
{
  transition: 1s all ease-in-out;
  display: inline-block;
  position: absolute;
}

.account-setup 
{
  left: calc(50% - 175px);
}

.finish-step, .user-details 
{
  left: 1400px;
}

.line 
{
  transition: all 1s ease-in-out;
  background: linear-gradient(to right, #6A1B9A 50%, white 50%);
  background-size: 200% 100%;
  background-position: right bottom;
}

.line-active 
{
  background-position: left bottom;
}

label 
{
  display: block;
  text-align: left;
  font-size: 0.8em;
  padding-left: 5px;
  color: #444;
}

input[type=checkbox]
{
  display: none;
}

.accept-conditions 
{
  margin-top: 20px;
}

input::placeholder 
{ 
  color: black;
}


@media(max-width: 600px) 
{
  .step-bar 
  {
    display: none;
  }
}
</style>

<body>
    
    
    <h1>ACCOUNT SETUP</h1>
  <div class="account-setup register-form">
    <h2>FOLLOW THE STEPS BELOW</h2>
    <form action="signup" method="post">
    <input name="email" type="text" placeholder="Email" required/>
    <input name="password" type="password" placeholder="Password" required/>
    <input name="confirmPassword" type="password" placeholder="Confirm Password" required/>
    <input name="name" type="text" placeholder="Full name" required/>
    <input name="country" type="text" placeholder="Country" required/>
    <input name="city" type="text" placeholder="City" required/>
    
  
    <label for="mobile">Mobile Number</label>
    <input name="mobile" type="number" required/>
    <div class="error-text">*This field is required</div>
    <input type="submit" value="Register"/>
 
</form>
  <script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
  
</body>
</html>
