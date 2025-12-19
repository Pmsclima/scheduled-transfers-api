package com.pedro.scheduledtransfers.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ScheduledTransferResponse(
        Long id,
        BigDecimal amount,
        LocalDate scheduleDate,
        BigDecimal fee,
        BigDecimal totalAmount
) {}