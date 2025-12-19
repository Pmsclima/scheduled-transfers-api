package com.pedro.scheduledtransfers.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "scheduled_transfer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduledTransferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;
    @Column(nullable = false)
    private LocalDate scheduleDate;
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal fee;
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal totalAmount;
}