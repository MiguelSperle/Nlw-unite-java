package com.miguelsperle.passin.entities.attendee.exceptions;

public class AttendeeNotFoundException extends RuntimeException{
    public  AttendeeNotFoundException(String message){
        super(message);
    }
}
