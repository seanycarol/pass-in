package com.seanycarol.passin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seanycarol.passin.domain.checkin.CheckIn;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {
}
