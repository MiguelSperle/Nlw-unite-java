package com.miguelsperle.passin.entities.attendee.exceptions;

public class AttendeeAlreadyExistsException extends RuntimeException{
    public AttendeeAlreadyExistsException(String message){
        super(message);
    }
}

