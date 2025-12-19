package com.pedro.scheduledtransfers.business.service;

import com.pedro.scheduledtransfers.dto.request.ScheduledTransferRequest;
import com.pedro.scheduledtransfers.dto.response.PaginationResponse;
import com.pedro.scheduledtransfers.dto.response.ScheduledTransferResponse;
import org.springframework.data.domain.Pageable;

public interface ScheduledTransferContract {

    ScheduledTransferResponse create(final ScheduledTransferRequest scheduledTransferRequest);

    ScheduledTransferResponse getById(final Long id);

    PaginationResponse<ScheduledTransferResponse> getAll(final Pageable pageable);
}