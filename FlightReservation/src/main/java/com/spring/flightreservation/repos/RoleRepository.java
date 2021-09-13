package com.spring.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.flightreservation.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
