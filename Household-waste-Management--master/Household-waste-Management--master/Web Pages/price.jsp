<!DOCTYPE html>
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title>Pricing table </title> 
</head>
<style>
	@import url('https://fonts.googleapis.com/css?family=Roboto:300,400');

body{
    margin: 0;
    padding: 0;
    font-family: 'Roboto', sans-serif !important;
    background-image: url("Selling-Scrap-Online.png");
 
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
section{
    width: 100%;
    height: 100vh;
    box-sizing: border-box;
    padding: 140px 0;

}
.card{
    position: relative;
    max-width: 300px;
    height: auto;
    border-radius: 40px;
    margin: 0 auto;
    padding: 40px 20px;
    box-shadow: inset 0 0 40px rgba(0,0,0,.2), 0 20px 50px rgba(0,0,0,.5);
    transition: .5s;
    overflow: hidden;
}
.card:hover{
    transform: scale(1.1);
}
.col-sm-4:nth-child(1) .card,
.col-sm-4:nth-child(1) .card .title .fa-telegram-plane{
    background: linear-gradient(-45deg,#f403d1,#64b5f6);
}

.col-sm-4:nth-child(2) .card,
.col-sm-4:nth-child(2) .card .title .fas{
    background: linear-gradient(-45deg,#ffec61,#f321d7);
}
.col-sm-4:nth-child(3) .card{
    background: linear-gradient(-45deg,#24ff72,#9a4eff);
}
.card:before{
    content: '';
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 40%;
    background: rgba(255,255,255,.1);
    z-index: 1;
    transform: skewY(-5deg) scale(1.5);
}
.title .fa-telegram-plane{
    color: #fff;
    font-size: 60px;
    width: 100px;
    height: 100px;
    border-radius: 50%;
    text-align: center;
    line-height: 100px;
    box-shadow: 0 10px 10px rgba(0,0,0,.5);
}
.title .fas{
    color: #fff;
    font-size: 60px;
    width: 100px;
    height: 100px;
    border-radius: 50%;
    text-align: center;
    line-height: 100px;
    box-shadow: 0 10px 10px rgba(0,0,0,.5);
}
.title h2{
    position: relative;
    margin: 20px 0 0;
    padding: 0;
    color: #fff;
    font-size: 28px;
    z-index: 2;
}
.price{
    position: relative;
    z-index: 2;
}
.price h4{
    margin: 0;
    padding: 20px 0;
    color: #fff;
    font-size: 60px;
}
.option{
    position: relative;
    z-index: 2;
}
.option ul{
    margin: 0;
    padding: 0;
}
.option ul li{
    margin: 0;
    padding: 0;
    list-style: none;
    color: #fff;
    font-size: 16px;
}
.card a{
    position: relative;
    z-index: 2;
    background: #fff;
    border-radius: 40px;
    width: 150px;
    height: 40px;
    line-height: 40px;
    display: block;
    text-align: center;
    margin: 20px auto 0;
    font-size: 16px;
    cursor: pointer;
    box-shadow: 0 5px 10px rgba(0,0,0,.1);
}
.cord a:hover{
    text-decoration: none;

}
</style>

<body>

  <html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Pricing </title>
    
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css">
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

    <section>

        <div class="container-fluid">
            <div class="container">
                <div class="row">
                    <div class="col-sm-4">
                        <div class="card text-center">
                            <div class="title">
                                <i class="fab fa-telegram-plane"></i>
                                <h2>BIGGER STUFF</h2>
                            </div>
                            <div class="price">
                                <h4> <sup>Rs</sup>100/kg.</h4>
                            </div>
                            <div class="option">
                                <ul>
                                    <li><i class="fas fa-check"></i> ALUMINIUM</li>
                                    <li><i class="fas fa-check"></i> BRASS</li>
                                    <li><i class="fas fa-check"></i> COPPER</li>
                                    <li><i class="fas fa-check"></i> STEEL</li>
                                    <li><i class="fas fa-check"></i> IRON</li>

                                </ul>
                            </div>
                            <a href="pickup.jsp">I ACCEPT</a>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="card text-center">
                            <div class="title">
                                <i class="fas fa-plane"></i>
                                <h2>PLASTICS</h2>
                            </div>
                            <div class="price">
                                <h4> <sup>Rs</sup>30 per piece</h4>
                            </div>
                            <div class="option">
                                <ul>
                                    <li><i class="fas fa-check"></i>Plastic Jars</li>
                                    <li><i class="fas fa-check"></i>Milk Cans</li>
                                    <li><i class="fas fa-check"></i>Cartons/Boxes</li>
                                    <li><i class="fas fa-check"></i>Bottles</li>
                                    <li><i class="fas fa-check"></i>E-Waste</li>
                                </ul>
                            </div>
                            <a href="pickup.jsp">I ACCEPT</a>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="card text-center">
                            <div class="title">
                                <i class="fas fa-rocket"></i>
                                <h2>PAPER MATERIAL</h2>
                            </div>
                            <div class="price">
                                <h4> <sup>Rs</sup>10/kg</h4>
                            </div>
                            <div class="option">
                                <ul>
                                    <li><i class="fas fa-check"></i>Books </li>
                                    <li><i class="fas fa-check"></i>Newspapers</li>
                                    <li><i class="fas fa-check"></i>Notebooks</li>
                                    <li><i class="fas fa-check"></i>Magazines</li>
                                </ul>
                            </div>
                            <a href="pickup.jsp">I ACCEPT</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

</body>
</html>
