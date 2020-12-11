<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>

    <nav>
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="index.jsp">myJSP</a>
            </div>
            <ul class="nav navbar-nav">
                
            <% 
                if (session.getAttribute("username") == null) { %>
            
                <li class="active"><a href="index.jsp">Home</a></li>
                <li><a href="about.jsp">About</a></li>
                <li><a href="#">Page 1</a></li>
                <li><a href="#">Page 2</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="signup.jsp"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </ul>
            
            <% } else { //visible for logged in users%>
            
                <li class="active"><a href="welcomePage.jsp">Home</a></li>
                <li><a href="booking.jsp">Bookings</a></li>
                
                <% if (session.getAttribute("user_type").equals("admin")) { //only admin user can see%> 
                <li><a href="approveBooking.jsp">Approve Booking</a></li>
                <li><a href="userTable.jsp">Users Table</a></li>
                 <% } %>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="logout.jsp">Logout</a></li>
            </ul>
                
            <% } %>
        </div>
    </nav>
