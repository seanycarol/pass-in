package com.seanycarol.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seanycarol.passin.domain.attendee.Attendee;

public interface AttendeeRepository extends JpaRepository<Attendee, String>{
}
