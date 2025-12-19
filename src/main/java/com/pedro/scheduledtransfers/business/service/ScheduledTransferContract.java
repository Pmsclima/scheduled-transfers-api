package com.pedro.scheduledtransfers.business.service;

import com.pedro.scheduledtransfers.dto.request.ScheduledTransferRequest;
import com.pedro.scheduledtransfers.dto.response.ScheduledTransferResponse;

public interface ScheduledTransferContract {

    ScheduledTransferResponse create(final ScheduledTransferRequest scheduledTransferRequest);
}