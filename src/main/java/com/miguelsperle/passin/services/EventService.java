package com.miguelsperle.passin.services;

import com.miguelsperle.passin.dtos.event.CreateEventDTO;
import com.miguelsperle.passin.dtos.event.EventIdDTO;
import com.miguelsperle.passin.dtos.event.EventResponseDTO;
import com.miguelsperle.passin.entities.event.Event;
import com.miguelsperle.passin.entities.event.exceptions.EventAlreadyExistsException;
import com.miguelsperle.passin.entities.event.exceptions.EventNotFoundExceptions;
import com.miguelsperle.passin.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.Normalizer;

@Service
@RequiredArgsConstructor // Essa notação já faz de forma automatica a criação do constructor, passando todos os atributos
public class EventService {
    private final EventRepository eventRepository; // Esse final significa que não vai ter nenhuma alteração depois de declarado
    private final AttendeeService attendeeService;

    public EventResponseDTO getEventDetail(String eventId) {
        var event = this.eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundExceptions("Event not found with ID: " + eventId));

        var attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);

        return new EventResponseDTO(
                event.getId(), event.getTitle(), event.getDetails(), event.getSlug(), event.getMaximumAttendees(), attendeeList.size());
    }

    public EventIdDTO createEvent(CreateEventDTO createEventDTO) {
        var newEvent = new Event();

        var verificationExistsAlreadyEvent = this.eventRepository.findByTitle(createEventDTO.title());

        if(verificationExistsAlreadyEvent != null){
           throw new EventAlreadyExistsException("Event already exists");
        }

        newEvent.setTitle(createEventDTO.title());
        newEvent.setDetails(createEventDTO.details());
        newEvent.setMaximumAttendees(createEventDTO.maximumAttendees());
        newEvent.setSlug(this.createSlug(createEventDTO.title()));

        this.eventRepository.save(newEvent);

        return new EventIdDTO(newEvent.getId());
    }

    private String createSlug(String text) {
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "") // Substitui os acentos por string vazia
                .replaceAll("[^\\w\\s]", "") // Seleciona os caracteres que nao for letra e numero e vamos tirar
                .replaceAll("\\s+", "-") // Substitui todos os espacos por hifen
                .toLowerCase();
    }
}
