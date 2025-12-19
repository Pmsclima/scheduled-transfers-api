package com.pedro.scheduledtransfers.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ScheduledTransferRequest(
        @NotNull @Positive BigDecimal amount,
        @NotNull @FutureOrPresent LocalDate scheduleDate
) {}