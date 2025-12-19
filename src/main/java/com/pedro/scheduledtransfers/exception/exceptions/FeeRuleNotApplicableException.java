package com.pedro.scheduledtransfers.exception.exceptions;

public class FeeRuleNotApplicableException extends RuntimeException {
    public FeeRuleNotApplicableException(String message) {
        super(message);
    }
}