package com.miguelsperle.passin.controllers;

import com.miguelsperle.passin.dtos.attendee.AttendeeBadgeDTO;
import com.miguelsperle.passin.response.ResponseHandler;
import com.miguelsperle.passin.services.AttendeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/attendees")
@RequiredArgsConstructor
public class AttendeeController {
    private final AttendeeService attendeeService;

    @GetMapping("/{attendeeId}/badge")
    public ResponseEntity<Object> getAttendeeBadge(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder){
        AttendeeBadgeDTO attendeeBadgeDTO =  this.attendeeService.getAttendeeBadge(attendeeId, uriComponentsBuilder);

        return ResponseHandler.generateResponse(attendeeBadgeDTO, HttpStatus.OK);
    }
}
