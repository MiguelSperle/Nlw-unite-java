package com.miguelsperle.passin.controllers;

import com.miguelsperle.passin.dtos.event.CreateEventDTO;
import com.miguelsperle.passin.dtos.event.EventIdDTO;
import com.miguelsperle.passin.response.ResponseHandler;
import com.miguelsperle.passin.services.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getEvent(@PathVariable String id){
        var event = this.eventService.getEventDetail(id);

        return ResponseHandler.generateResponse(event, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createEvent(@RequestBody @Valid CreateEventDTO createEventDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseHandler.generateResponse(String.valueOf(bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().get()), HttpStatus.BAD_REQUEST);
        }

        EventIdDTO eventId = this.eventService.createEvent(createEventDTO);

        return ResponseHandler.generateResponse(eventId, HttpStatus.CREATED);
    }
}
