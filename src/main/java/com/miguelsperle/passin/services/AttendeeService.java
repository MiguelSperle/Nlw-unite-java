package com.miguelsperle.passin.services;

import com.miguelsperle.passin.dtos.attendee.AttendeeResponseDTO;
import com.miguelsperle.passin.entities.attendee.Attendee;
import com.miguelsperle.passin.entities.checkin.CheckIn;
import com.miguelsperle.passin.repositories.AttendeeRepository;
import com.miguelsperle.passin.repositories.CheckInRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final CheckInRepository checkInRepository;

    public List<Attendee> getAllAttendeesFromEvent(String eventId){
       return this.attendeeRepository.findAllByEventId(eventId);
    }

    public List<AttendeeResponseDTO> getEventsAttendee(String eventId){
        var attendeeList = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeResponseDTO> attendeeDetailList =  attendeeList.stream().map(attendee -> {
           Optional<CheckIn> checkIn = this.checkInRepository.findByAttendeeId(attendee.getId());
           LocalDateTime checkedInAt = checkIn.<LocalDateTime>map(CheckIn::getCreatedAt).orElse(null);
           return new AttendeeResponseDTO(attendee.getId(), attendee.getName(), attendee.getEmail(), attendee.getCreatedAt(), checkedInAt);
        }).toList();

        return attendeeDetailList;
    }
}