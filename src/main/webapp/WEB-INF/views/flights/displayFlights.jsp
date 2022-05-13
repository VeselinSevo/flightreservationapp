<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
   <head>
      <title>Find Flights</title>
   </head>

   <body>
      <h2>Find Flights:</h2>
      <table>
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
            <td><a href="show-booking?flightId=${flight.id}">Book this flight</a></td>
         </tr>
         </c:forEach>
      </table>
   </body>
</html>