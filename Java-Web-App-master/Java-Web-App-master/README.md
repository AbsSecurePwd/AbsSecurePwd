# JSP Web App

A  basic booking java web application where a user can register/login to request bookings and the admin user approves the booking.<br /><br />

<p float="left">
  <img src="/myJSP/screenshots/signup.jpg" width="250" height="200" />
  <img src="/myJSP/screenshots/login.jpg" width="250" height="200" />
  <img src="/myJSP/screenshots/index.jpg" width="250" height="200" />
</p>
<p float="left">
  <img src="/myJSP/screenshots/booking.jpg" width="250" height="200" />
  <img src="/myJSP/screenshots/approve_booking.jpg" width="250" height="200" />
  <img src="/myJSP/screenshots/user_table.jpg" width="250" height="200" />
</p>

## Features
- Login/registration system - allowing user to register and sign to make bookings. 
- Basic booking system – where users make simple request for a date of choice and waits for admin for approval. 

User account:
- Make a booking request.
- Cancel booking request.

Admin account:
- Approve bookings
- View all registered users with search bar

Note: By default all signup accounts are type user 
Have to manually change user type to admin in the database to get admin access

## Setup
- Open Netbeans
- Open project
- Connect to jdbc:derby://localhost:1527/sample under Servies tab > Databases
- Create USERS and BOOKING tables by executing SQL commands in the create_table.sql file
- Right click project name, click run.


