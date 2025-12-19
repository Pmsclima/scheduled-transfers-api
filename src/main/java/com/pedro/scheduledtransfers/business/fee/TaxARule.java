package com.pedro.scheduledtransfers.business.fee;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Component
public class TaxARule implements FeeRule{

    private static final BigDecimal MAX = new BigDecimal("1000.00");
    private static final BigDecimal RATE = new BigDecimal("0.03");
    private static final BigDecimal FIXED = new BigDecimal("3.00");

    @Override
    public boolean applies(final BigDecimal amount,final LocalDate scheduleDate,final LocalDate today) {
        return scheduleDate.isEqual(today)
                && amount.compareTo(BigDecimal.ZERO) >= 0
                && amount.compareTo(MAX) <= 0;
    }

    @Override
    public FeeResult calculate(final BigDecimal amount) {
        final BigDecimal fee = amount.multiply(RATE)
                .add(FIXED)
                .setScale(2, RoundingMode.HALF_UP);

        final BigDecimal total = amount.add(fee).setScale(2, RoundingMode.HALF_UP);

        return new FeeResult(fee, total);
    }
}