package com.pedro.scheduledtransfers.business.fee;

import com.pedro.scheduledtransfers.exception.exceptions.FeeRuleNotApplicableException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FeeCalculator {

    private final List<FeeRule> rules;

    public FeeResult calculate(final BigDecimal amount,final LocalDate scheduleDate) {
        final LocalDate today = LocalDate.now();

        return rules.stream()
                .filter(rule -> rule.applies(amount, scheduleDate, today))
                .findFirst()
                .map(rule -> rule.calculate(amount))
                .orElseThrow(() -> new FeeRuleNotApplicableException(
                        "No fee rule matched for the provided amount and scheduleDate."
                ));
    }
}