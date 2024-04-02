package com.miguelsperle.passin.entities.checkin;

import com.miguelsperle.passin.entities.attendee.Attendee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "check_ins")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne // Um participante só pode ter um checkin
    @JoinColumn(name = "attendee_id", nullable = false) // representa a relação com outra tabela
    private Attendee attendee;
}
