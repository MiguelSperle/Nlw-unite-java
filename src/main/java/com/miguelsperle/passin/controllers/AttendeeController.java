package com.miguelsperle.passin.controllers;

import com.miguelsperle.passin.dtos.attendee.AttendeeBadgeDTO;
import com.miguelsperle.passin.response.ResponseHandler;
import com.miguelsperle.passin.services.AttendeeService;
import com.miguelsperle.passin.services.CheckInService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/{attendeeId}/check-in")
    public ResponseEntity<Object> registerCheckIn(@PathVariable String attendeeId, UriComponentsBuilder uriComponentsBuilder){
      this.attendeeService.checkInAttendee(attendeeId);

     // var uri = uriComponentsBuilder.path("/attendees/{attendeeId}/badge").buildAndExpand(attendeeId).toUri();

      return ResponseHandler.generateResponse("Seu check-in foi feito", HttpStatus.CREATED);
    }
}
