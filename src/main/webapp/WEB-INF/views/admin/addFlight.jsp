<html>
   <head>
      <title>Add Flight</title>
   </head>

   <body>
      <h1>Add Flight</h1>

      <form action = "add-flight" method = "post">
      <pre>
      Flight Number: <input type = "text" name = "flightNumber"><br/>
      Operating Airlines: <input type = "text" name = "operatingAirlines"><br/>
      Departure City: <input type = "text" name = "departureCity"><br/>
      Arrival City: <input type = "text" name = "arrivalCity"<br/>
      Departure Date: <input type = "text" name = "departureDate"<br/>
      Departure Estimated Time: <input type = "text" name = "estimatedDepartureTime"<br/>
      <input type = "submit" value = "Add"/>
      </pre>
      </form>

      ${msg}
      <a href="display-flights">View All</a>
   </body>
</html>