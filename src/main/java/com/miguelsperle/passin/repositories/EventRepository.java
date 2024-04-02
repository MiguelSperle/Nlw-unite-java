package com.miguelsperle.passin.repositories;

import com.miguelsperle.passin.entities.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {

}
