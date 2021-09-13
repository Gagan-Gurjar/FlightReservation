package com.spring.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.flightreservation.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	@Query("from Reservation where id=:id")
	Reservation findOne(@Param("id")Long id);

}
