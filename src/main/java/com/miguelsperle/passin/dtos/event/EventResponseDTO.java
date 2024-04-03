package com.miguelsperle.passin.dtos.event;

public record EventResponseDTO(
        String id,
        String title,
        String details,
        String slug,
        Integer maximumAttendees,
        Integer attendeesAmount) {
}
