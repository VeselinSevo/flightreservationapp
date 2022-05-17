<html>
   <head>
      <title>Register User</title>
   </head>

   <body>
      <h1>Complete Reservation:</h1>
      <h2>Flight Info:</h2>
          <pre>
              Flight Id: ${flight.flightNumber}
              Airline: ${flight.operatingAirlines}
              Departure City: ${flight.departureCity}
              Arrival City: ${flight.arrivalCity}
              Departure time: ${flight.estimatedDepartureTime}
          </pre>
      <h2>Passenger Details:</h2>
          <form action = "book" method="post">
              <pre>
                  First Name: <input type="text" name="passengerFirstName" placeholder="First Name"><br/>
                  Last Name: <input type="text"  name="passengerLastName" placeholder="Last Name"><br/>
                  Email: <input type="text"  name="passengerEmail" placeholder="Email"><br/>
                  Phone: <input type="text" name ="passengerPhone" placeholder="Phone"><br/>
              </pre>
      <h2>Card Details:</h2>
          <pre>
                   Card Number: <input type="text" name="cardNumber" placeholder="Card Number"><br/>
                   Expiration Date: <input type="text" name="expirationDate" placeholder="Expiration Date"><br/>
                   CVV: <input type="text" name="cvv" placeholder="CVV"><br/>
                   <input type="hidden" name="flightId" value="${flight.id}">
                   <input type="submit" value="submit">
          </pre>
      </form>
   </body>
</html>