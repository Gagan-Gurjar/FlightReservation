package com.spring.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.flightreservation.entity.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
