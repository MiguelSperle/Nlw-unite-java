package com.miguelsperle.passin.entities.attendee;


import com.miguelsperle.passin.entities.event.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "attendees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendee {
    @Id
    @Column(nullable = false) // Essa coluna não pode ser nula
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false) // Essa coluna não pode ser nula
    private String name;

    @Column(nullable = false) // Essa coluna não pode ser nula
    private String email;

    @ManyToOne // Um participante pode estar em varios eventos
    @JoinColumn(name = "event_id", nullable = false) // representa a relação com outra tabela
    private Event event; // criando a relacão entre o attendee e o evento

    @Column(name = "created_at") // Essa coluna não pode ser nula
    private LocalDateTime createdAt;
}
