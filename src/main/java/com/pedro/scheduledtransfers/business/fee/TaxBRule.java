package com.pedro.scheduledtransfers.business.fee;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class TaxBRule implements FeeRule {

    private static final BigDecimal MIN = new BigDecimal("1001.00");
    private static final BigDecimal MAX = new BigDecimal("2000.00");
    private static final BigDecimal RATE = new BigDecimal("0.09");

    @Override
    public boolean applies(BigDecimal amount, LocalDate scheduleDate, LocalDate today) {
        final long daysBetween = ChronoUnit.DAYS.between(today, scheduleDate);

        return amount.compareTo(MIN) >= 0
                && amount.compareTo(MAX) <= 0
                && daysBetween >= 1
                && daysBetween <= 10;
    }

    @Override
    public FeeResult calculate(BigDecimal amount) {
        final BigDecimal fee = amount
                .multiply(RATE)
                .setScale(2, RoundingMode.HALF_UP);

        final BigDecimal total = amount
                .add(fee)
                .setScale(2, RoundingMode.HALF_UP);

        return new FeeResult(fee, total);
    }
}