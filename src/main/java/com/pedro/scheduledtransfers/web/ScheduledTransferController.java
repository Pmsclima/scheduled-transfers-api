package com.pedro.scheduledtransfers.web;

import com.pedro.scheduledtransfers.business.service.ScheduledTransferContract;
import com.pedro.scheduledtransfers.dto.request.ScheduledTransferRequest;
import com.pedro.scheduledtransfers.dto.response.PaginationResponse;
import com.pedro.scheduledtransfers.dto.response.ScheduledTransferResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transfers")
@RequiredArgsConstructor
public class ScheduledTransferController {

    private final ScheduledTransferContract scheduledTransferContract;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduledTransferResponse create(
            @Valid @RequestBody final ScheduledTransferRequest scheduledTransferRequest
    ) {
        return scheduledTransferContract.create(scheduledTransferRequest);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduledTransferResponse> getById(@PathVariable final Long id) {
        return new ResponseEntity<>(scheduledTransferContract.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PaginationResponse<ScheduledTransferResponse>> getAll(
            @PageableDefault(page = 0, size = 10) final Pageable pageable
    ) {
        return new ResponseEntity<>(scheduledTransferContract.getAll(pageable), HttpStatus.OK);
    }
}