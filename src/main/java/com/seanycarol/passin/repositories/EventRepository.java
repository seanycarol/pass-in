package com.seanycarol.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seanycarol.passin.domain.event.Event;

public interface EventRepository extends JpaRepository<Event, String>{
}
