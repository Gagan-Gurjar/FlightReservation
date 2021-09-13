package com.spring.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.flightreservation.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

}
