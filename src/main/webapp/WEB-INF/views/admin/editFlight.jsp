<html>
   <head>
      <title>Edit Flights</title>
   </head>

   <body>
      <h1>Edit Flight</h1>

      <form action = "edit-flight" method = "post">
      <pre>
      Flight Id: <input type = "text" name = "flightId" value="${flight.id}" placeholder="${flight.id}" readonly="true"><br/>
      Flight Number: <input type = "text" name = "flightNumber" value="${flight.flightNumber}"><br/>
      Operating Airlines: <input type = "text" name = "operatingAirlines" value="${flight.operatingAirlines}"><br/>
      Departure City: <input type = "text" name = "departureCity" value="${flight.departureCity}"><br/>
      Arrival City: <input type = "text" name = "arrivalCity" value="${flight.arrivalCity}"><br/>
      Departure Date: <input type = "text" name = "departureDate" value="${flight.departureDate}"><br/>
      Departure Estimated Time: <input type = "text" name = "estimatedDepartureTime" value="${flight.estimatedDepartureTime}"><br/>
      <input type = "submit" value = "Confirm"/>
      </pre>
      </form>

      ${msg}
      <a href="display-flights">View All</a>
   </body>
</html>