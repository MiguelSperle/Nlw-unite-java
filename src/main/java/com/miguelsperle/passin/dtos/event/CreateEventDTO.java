package com.miguelsperle.passin.dtos.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateEventDTO(
        @NotBlank(message = "É necessário colocar um título para o evento") String title,
        @NotBlank(message = "É necessário colocar detalhes sobre o evento") String details,
        @NotNull(message = "É necessário colocar colocar a quantidade máxima de pessoas permitidas no evento") Integer maximumAttendees) {
}
