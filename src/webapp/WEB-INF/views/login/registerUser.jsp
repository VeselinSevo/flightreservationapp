<html>
   <head>
      <title>Register User</title>
      <link href="style.css" rel="stylesheet"/>
   </head>

   <body>
      <h1>Register</h1>
      <form action = "/registerUser" method="post">
          <pre>
              Id: ${newLocationId}<br/>
              First Name: <input type="text" name="firstName" placeholder="First Name"><br/>
              Last Name: <input type="text"  name="lastName" placeholder="Last Name"><br/>
              Password: <input type="text" name ="password" placeholder="Password"><br/>
              Confirm Password: <input type="text" name="confirmPassword" placeholder="Confirm Password"><br/>
              Email: <input type ="text" name="email" placeholder="Email"><br/>
              Phone: <input type ="text" name="phone" placeholder="Phone"><br/>
                     <input type ="submit" value = "register"><br/>
          </pre>
      </form>
   </body>
</html>