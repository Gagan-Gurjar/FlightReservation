package com.spring.flightreservation.services;

import com.spring.flightreservation.dto.ReservationRequest;
import com.spring.flightreservation.entity.Reservation;

public interface ReservationService {

	public Reservation bookFlight(ReservationRequest request);
}
