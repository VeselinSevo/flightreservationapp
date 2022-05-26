<html>
   <head>
      <title>Register User</title>
   </head>

   <body>
      <h2>Register:</h2>
      <form action = "register" method="post">
          <pre>
              First Name: <input type="text" name="firstName" placeholder="First Name"><br/>
              Last Name: <input type="text"  name="lastName" placeholder="Last Name"><br/>
              User Name: <input type="text"  name="email" placeholder="Enter Your Email"><br/>
              Password: <input type="password" name ="password" placeholder="Password"><br/>
              Confirm Password: <input type="password" name="confirmPassword" placeholder="Confirm Password"><br/>
              Would you like to get notified when new flight is added? <input type = "checkbox" name = "subscribed" value="subscribed"><br/>
              <input type ="submit" value = "register"><br/>
          </pre>
      </form>
   </body>
</html>