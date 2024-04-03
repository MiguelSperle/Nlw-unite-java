package com.miguelsperle.passin.entities.event.exceptions;

public class EventAlreadyExistsException extends RuntimeException  {
    public EventAlreadyExistsException(String message){
        super(message); // Passando a mensagem para o constructor da super classe, nesse caso RuntimeExecption
    }
}
