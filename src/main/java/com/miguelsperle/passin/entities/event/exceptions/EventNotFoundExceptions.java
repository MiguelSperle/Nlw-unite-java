package com.miguelsperle.passin.entities.event.exceptions;

public class EventNotFoundExceptions extends RuntimeException {
    public EventNotFoundExceptions(String message){
        super(message); // Passando a mensagem para o constructor da super classe, nesse caso RuntimeExecption
    }
}
