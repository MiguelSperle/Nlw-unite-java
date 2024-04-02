package com.miguelsperle.passin.repositories;

import com.miguelsperle.passin.entities.checkin.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckInRepository extends JpaRepository<CheckIn, Integer> {
}
