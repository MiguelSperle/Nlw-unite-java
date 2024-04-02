package com.miguelsperle.passin.repositories;

import com.miguelsperle.passin.entities.attendee.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeRepository extends JpaRepository<Attendee, String> {
}
