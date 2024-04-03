package com.miguelsperle.passin.dtos.attendee;

import java.time.LocalDateTime;

public record AttendeeResponseDTO(
        String id,
        String name,
        String email,
        LocalDateTime createdAt,
        LocalDateTime checkedInAt) {
}
