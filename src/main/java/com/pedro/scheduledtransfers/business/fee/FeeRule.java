package com.pedro.scheduledtransfers.business.fee;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface FeeRule {

    boolean applies(final BigDecimal amount,final LocalDate scheduleDate,final LocalDate today);

    FeeResult calculate(final BigDecimal amount);
}