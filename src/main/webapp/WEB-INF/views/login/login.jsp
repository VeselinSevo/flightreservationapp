<html>
   <head>
      <title>Login User</title>
   </head>

   <body>
      <h2>Login:</h2>
      <form action = "login" method="post">
          <pre>
              User Name: <input type="text"  name="email" placeholder="Enter Your Email"><br/>
              Password: <input type="password" name ="password" placeholder="Password"><br/>
              <input type ="submit" value = "login"><br/>
              ${error}
          </pre>
      </form>
   </body>
</html>