package com.miguelsperle.passin.services;

import com.miguelsperle.passin.dtos.attendee.AttendeeIdDTO;
import com.miguelsperle.passin.dtos.attendee.CreateAttendeeDTO;
import com.miguelsperle.passin.dtos.event.CreateEventDTO;
import com.miguelsperle.passin.dtos.event.EventIdDTO;
import com.miguelsperle.passin.dtos.event.EventResponseDTO;
import com.miguelsperle.passin.entities.attendee.Attendee;
import com.miguelsperle.passin.entities.event.Event;
import com.miguelsperle.passin.entities.event.exceptions.EventAlreadyExistsException;
import com.miguelsperle.passin.entities.event.exceptions.EventFullException;
import com.miguelsperle.passin.entities.event.exceptions.EventNotFoundExceptions;
import com.miguelsperle.passin.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor // Essa notação já faz de forma automatica a criação do constructor, passando todos os atributos
public class EventService {
    private final EventRepository eventRepository; // Esse final significa que não vai ter nenhuma alteração depois de declarado
    private final AttendeeService attendeeService;

    public EventResponseDTO getEventDetail(String eventId) {
        var event = this.getEventById(eventId);

        var attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);

        return new EventResponseDTO(
                event.getId(), event.getTitle(), event.getDetails(), event.getSlug(), event.getMaximumAttendees(), attendeeList.size());
    }

    public EventIdDTO createEvent(CreateEventDTO createEventDTO) {
        var newEvent = new Event();

        var verificationExistsAlreadyEvent = this.eventRepository.findByTitle(createEventDTO.title());

        if(verificationExistsAlreadyEvent.isPresent()){
           throw new EventAlreadyExistsException("Event already exists");
        }

        newEvent.setTitle(createEventDTO.title());
        newEvent.setDetails(createEventDTO.details());
        newEvent.setMaximumAttendees(createEventDTO.maximumAttendees());
        newEvent.setSlug(this.createSlug(createEventDTO.title()));

        this.eventRepository.save(newEvent);

        return new EventIdDTO(newEvent.getId());
    }

    public AttendeeIdDTO registerAttendeeOnEvent(String eventId, CreateAttendeeDTO createAttendeeDTO){
        this.attendeeService.verifyAttendeeSubscription(createAttendeeDTO.email(), eventId);

        var event = this.getEventById(eventId);

        var attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);

        if(event.getMaximumAttendees() <= attendeeList.size()) throw new EventFullException("Event is full");

        var newAttendee = new Attendee();

        newAttendee.setName(createAttendeeDTO.name());
        newAttendee.setEmail(createAttendeeDTO.email());
        newAttendee.setEvent(event);
        newAttendee.setCreatedAt(LocalDateTime.now());

        this.attendeeService.registerAttendee(newAttendee);

        return new AttendeeIdDTO(newAttendee.getId());
    }

    private Event getEventById(String eventId){
        return this.eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundExceptions("Event not found with ID: " + eventId));
    }

    private String createSlug(String text) {
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "") // Substitui os acentos por string vazia
                .replaceAll("[^\\w\\s]", "") // Seleciona os caracteres que nao for letra e numero e vamos tirar
                .replaceAll("\\s+", "-") // Substitui todos os espacos por hifen
                .toLowerCase();
    }
}
