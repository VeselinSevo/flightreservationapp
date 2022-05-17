package com.codeinsight.flightreservation.flightreservation.services;

import com.codeinsight.flightreservation.flightreservation.dto.ReservationRequest;
import com.codeinsight.flightreservation.flightreservation.entities.Reservation;

public interface ReservationService {

    Reservation findReservationById(Long id);

    public Reservation bookFlight(ReservationRequest request);
}
