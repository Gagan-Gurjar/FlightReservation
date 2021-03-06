package com.spring.flightreservation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.flightreservation.controller.ReservationController;
import com.spring.flightreservation.dto.ReservationRequest;
import com.spring.flightreservation.entity.Flight;
import com.spring.flightreservation.entity.Passenger;
import com.spring.flightreservation.entity.Reservation;
import com.spring.flightreservation.repos.FlightRepository;
import com.spring.flightreservation.repos.PassengerRepository;
import com.spring.flightreservation.repos.ReservationRepository;
import com.spring.flightreservation.util.EmailUtil;
import com.spring.flightreservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Value("${itinerary.dirpath}")
	private String ITINERARY_DIR;

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	PDFGenerator pdfGenerator;
	
	@Autowired
	EmailUtil emailUtil;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
	
	
	
	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {
		
		LOGGER.info("INSIDE bookFlight()");
		
		// Make Payment
		
		Long flightId = request.getFlightId();
		LOGGER.info("Fetching flight for flight id: " + flightId);
		Flight flight = flightRepository.findOne(flightId);
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		LOGGER.info("Saving the passenger:" + passenger);
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		

		LOGGER.info("Saving the reservation:" + reservation);
		Reservation savedReservation = reservationRepository.save(reservation);
		
		String filePath = ITINERARY_DIR + savedReservation.getId() + ".pdf";

		LOGGER.info("Generating the Itinerary");
		pdfGenerator.generateItinerary(savedReservation, filePath);
		
		LOGGER.info("Sending the Itinerary");
		emailUtil.sendItinerary(passenger.getEmail(), filePath);
		return savedReservation;
	}

}











