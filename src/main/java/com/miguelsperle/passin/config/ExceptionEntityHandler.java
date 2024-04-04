package com.miguelsperle.passin.config;

import com.miguelsperle.passin.entities.attendee.exceptions.AttendeeAlreadyExistsException;
import com.miguelsperle.passin.entities.attendee.exceptions.AttendeeNotFoundException;
import com.miguelsperle.passin.entities.checkin.exceptions.CheckInAlreadyExistsException;
import com.miguelsperle.passin.entities.event.exceptions.EventAlreadyExistsException;
import com.miguelsperle.passin.entities.event.exceptions.EventFullException;
import com.miguelsperle.passin.entities.event.exceptions.EventNotFoundExceptions;
import com.miguelsperle.passin.response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(EventNotFoundExceptions.class)
    public ResponseEntity<Object> handleEventNotFound(EventNotFoundExceptions exceptions){
        return ResponseHandler.generateResponse(exceptions.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventAlreadyExistsException.class)
    public ResponseEntity<Object> handleEventAlreadyExists(EventAlreadyExistsException exception){
        return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EventFullException.class)
    public ResponseEntity<Object> handleEventFullException(EventFullException exception){
        return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AttendeeAlreadyExistsException.class)
    public ResponseEntity<Object> handleAttendeeAlreadyExists(AttendeeAlreadyExistsException exception){
        return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AttendeeNotFoundException.class)
    public ResponseEntity<Object> handleAttendeeNotFound(AttendeeNotFoundException exception){
        return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CheckInAlreadyExistsException.class)
    public ResponseEntity<Object> handleCheckInAlreadyExistsException(CheckInAlreadyExistsException exception){
        return ResponseHandler.generateResponse(exception.getMessage(), HttpStatus.CONFLICT);
    }
}
