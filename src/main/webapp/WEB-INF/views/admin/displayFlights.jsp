<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
   <head>
      <title>All Flights</title>
   </head>

   <body>
      <h2>All Flights:</h2>

      <form action = "search-flight-by-params">
      Flight Id: <input type="text" value="" name="flightId"><br/>
      Flight Number: <input type="text" value="" name="flightNumber"><br/>
      Operating Airlines: <input type="text" value="" name="operatingAirlines"><br/>
      Departure City: <input type="text" value="" name="departureCity"><br/>
      Arrival City: <input type="text" value="" name="arrivalCity"><br/>
      <input type = "submit" value = "Search"/>
      </form>

      <table>
         <tr>
            <th><button class="btn" type="button" onclick="flight.href='/sort-flight-by-flightNumber'">Sort</button></th>
            <th><button class="btn" type="button" onclick="flight.href='/sort-location-by-operatingAirlines'">Sort</button></th>
            <th><button class="btn" type="button" onclick="flight.href='/sort-location-by-departureCity '">Sort</button></th>
            <th><button class="btn" type="button" onclick="flight.href='/sort-location-by-arrivalCity'">Sort</button></th>
            <th><button class="btn" type="button" onclick="flight.href='/sort-location-by-departureDate'">Sort</button></th>
         </tr>
         <tr>
            <th>Flight Number:</th>
            <th>Operating Airlines:</th>
            <th>From:</th>
            <th>To:</th>
            <th>Departure Date:</th>
         </tr>
         <c:forEach var="flight" items="${flights}">
         <tr>
            <td>${flight.flightNumber}</td>
            <td>${flight.operatingAirlines}</td>
            <td>${flight.departureCity}</td>
            <td>${flight.arrivalCity}</td>
            <td>${flight.estimatedDepartureTime}</td>
            <td><a href="show-edit?flightId=${flight.id}">edit</a></td>
            <td><a href="delete-flight?flightId=${flight.id}">delete</a></td>
         </tr>
         </c:forEach>
      </table>
      <a href="display-flights">View All</a><br/>
      <a href="show-add">Add flight</a>
   </body>
</html>