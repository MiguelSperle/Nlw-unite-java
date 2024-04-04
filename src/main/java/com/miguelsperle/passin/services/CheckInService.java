package com.miguelsperle.passin.services;

import com.miguelsperle.passin.entities.attendee.Attendee;
import com.miguelsperle.passin.entities.checkin.CheckIn;
import com.miguelsperle.passin.entities.checkin.exceptions.CheckInAlreadyExistsException;
import com.miguelsperle.passin.repositories.CheckInRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CheckInService {
    private final CheckInRepository checkInRepository;


    public void registerCheckIn(Attendee attendee){
        this.verifyCheckInExists(attendee.getId());

        var newCheckIn = new CheckIn();

        newCheckIn.setAttendee(attendee);
        newCheckIn.setCreatedAt(LocalDateTime.now());

        this.checkInRepository.save(newCheckIn);
    }

    private void verifyCheckInExists(String attendeeId){
        Optional<CheckIn> isCheckedIn = this.getCheckIn(attendeeId);

        if(isCheckedIn.isPresent()) throw  new CheckInAlreadyExistsException("Attendee already checked in");
    }

    public Optional<CheckIn> getCheckIn(String attendeeId){
       return this.checkInRepository.findByAttendeeId(attendeeId);
    }
}
