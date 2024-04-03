package com.miguelsperle.passin.entities.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "events")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @Column(nullable = false) // Essa coluna não pode ser nula
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false) // Essa coluna não pode ser nula
    private String title;

    @Column(nullable = false) // Essa coluna não pode ser nula
    private String details;

    @Column(nullable = false, unique = true) // Essa coluna não pode ser nula
    private String slug;

    @Column(nullable = false, name="maximum_attendees") // Essa coluna não pode ser nula
    private Integer maximumAttendees;
}
