package com.miguelsperle.passin.config;

import com.miguelsperle.passin.entities.event.exceptions.EventAlreadyExistsException;
import com.miguelsperle.passin.entities.event.exceptions.EventNotFoundExceptions;
import com.miguelsperle.passin.response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(EventNotFoundExceptions.class)
    public ResponseEntity<Object> handleEventNotFound(){
        return ResponseHandler.generateResponse("Event not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EventAlreadyExistsException.class)
    public ResponseEntity<Object> handleEventAlreadyExists(){
        return ResponseHandler.generateResponse("Event already exists", HttpStatus.CONFLICT);
    }
}
