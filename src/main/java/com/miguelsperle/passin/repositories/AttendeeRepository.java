package com.miguelsperle.passin.repositories;

import com.miguelsperle.passin.entities.attendee.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendeeRepository extends JpaRepository<Attendee, String> {
    List<Attendee> findAllByEventId(String eventId);
    Optional<Attendee> findByEventIdAndEmail(String eventId, String email);
}
