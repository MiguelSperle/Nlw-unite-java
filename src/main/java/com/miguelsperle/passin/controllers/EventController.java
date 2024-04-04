package com.miguelsperle.passin.controllers;

import com.miguelsperle.passin.dtos.attendee.AttendeeIdDTO;
import com.miguelsperle.passin.dtos.attendee.AttendeeResponseDTO;
import com.miguelsperle.passin.dtos.attendee.CreateAttendeeDTO;
import com.miguelsperle.passin.dtos.event.CreateEventDTO;
import com.miguelsperle.passin.dtos.event.EventIdDTO;
import com.miguelsperle.passin.dtos.event.EventResponseDTO;
import com.miguelsperle.passin.response.ResponseHandler;
import com.miguelsperle.passin.services.AttendeeService;
import com.miguelsperle.passin.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final AttendeeService attendeeService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEvent(@PathVariable String id){
        EventResponseDTO eventResponseDTO = this.eventService.getEventDetail(id);

        return ResponseHandler.generateResponse(eventResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createEvent(@RequestBody @Valid CreateEventDTO createEventDTO, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {
        if (bindingResult.hasErrors()) {
            return ResponseHandler.generateResponse(String.valueOf(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().get()), HttpStatus.BAD_REQUEST);
        }

        EventIdDTO eventIdDTO = this.eventService.createEvent(createEventDTO);

        // var uri = uriComponentsBuilder.path("/events/{id}").buildAndExpand(eventIdDTO.eventId()).toUri();

        return ResponseHandler.generateResponse(eventIdDTO, HttpStatus.CREATED);
    }

    @GetMapping("/attendees/{id}") // Retorna todos os participantes do evento
    public ResponseEntity<Object> getEventAttendees(@PathVariable String id){
        List<AttendeeResponseDTO> attendeeResponseDTO = this.attendeeService.getEventsAttendee(id);

        return ResponseHandler.generateResponse(attendeeResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/{eventId}/attendees")
    public ResponseEntity<Object> registerParticipants(@PathVariable String eventId, @RequestBody @Valid CreateAttendeeDTO createAttendeeDTO, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {
        if (bindingResult.hasErrors()) {
            return ResponseHandler.generateResponse(String.valueOf(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().get()), HttpStatus.BAD_REQUEST);
        }

        AttendeeIdDTO attendeeIdDTO = this.eventService.registerAttendeeOnEvent(eventId, createAttendeeDTO);

        // var uri = uriComponentsBuilder.path("/attendees/{attendId}/badge").buildAndExpand(attendeeIdDTO.attendeeId()).toUri();

        return ResponseHandler.generateResponse(attendeeIdDTO, HttpStatus.CREATED);
    }
}
