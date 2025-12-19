package com.pedro.scheduledtransfers.business.fee;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class TaxC21To30Rule implements FeeRule {

    private static final BigDecimal MIN_EXCLUSIVE = new BigDecimal("2000.00");
    private static final BigDecimal RATE = new BigDecimal("0.069");

    @Override
    public boolean applies(final BigDecimal amount, final LocalDate scheduledDate, final LocalDate today) {
        final long daysBetween = ChronoUnit.DAYS.between(today, scheduledDate);

        return amount.compareTo(MIN_EXCLUSIVE) > 0
                && daysBetween >= 21
                && daysBetween <= 30;
    }

    @Override
    public FeeResult calculate(final BigDecimal amount) {
        final BigDecimal fee = amount.multiply(RATE).setScale(2, RoundingMode.HALF_UP);
        final BigDecimal total = amount.add(fee).setScale(2, RoundingMode.HALF_UP);

        return new FeeResult(fee, total);
    }
}