package com.pedro.scheduledtransfers.business.fee;

import java.math.BigDecimal;

public record FeeResult(
        BigDecimal fee,
        BigDecimal totalAmount
) {}