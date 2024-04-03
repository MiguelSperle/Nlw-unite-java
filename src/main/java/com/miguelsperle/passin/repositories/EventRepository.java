package com.miguelsperle.passin.repositories;

import com.miguelsperle.passin.entities.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, String> {
    Event findByTitle(String title);
}
