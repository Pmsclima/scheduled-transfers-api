package com.pedro.scheduledtransfers.business.service;

import com.pedro.scheduledtransfers.business.fee.FeeCalculator;
import com.pedro.scheduledtransfers.business.fee.FeeResult;
import com.pedro.scheduledtransfers.dto.request.ScheduledTransferRequest;
import com.pedro.scheduledtransfers.dto.response.PaginationResponse;
import com.pedro.scheduledtransfers.dto.response.ScheduledTransferResponse;
import com.pedro.scheduledtransfers.exception.exceptions.ResourceNotFoundException;
import com.pedro.scheduledtransfers.mapper.ScheduledTransferMapper;
import com.pedro.scheduledtransfers.persistence.entity.ScheduledTransferEntity;
import com.pedro.scheduledtransfers.persistence.repository.ScheduledTransferRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduledTransferService implements ScheduledTransferContract {

    private final ScheduledTransferRepository scheduledTransferRepository;
    private final ScheduledTransferMapper scheduledTransferMapper;
    private final FeeCalculator feeCalculator;

    @Override
    @Transactional
    public ScheduledTransferResponse create(final ScheduledTransferRequest scheduledTransferRequest) {
        final ScheduledTransferEntity scheduledTransferEntity =
                scheduledTransferMapper.toEntity(scheduledTransferRequest);

        final FeeResult feeResult = feeCalculator.calculate(
                scheduledTransferEntity.getAmount(),
                scheduledTransferEntity.getScheduleDate()
        );
        scheduledTransferEntity.setFee(feeResult.fee());
        scheduledTransferEntity.setTotalAmount(feeResult.totalAmount());

       final ScheduledTransferEntity savedScheduledTransferEntity =
                scheduledTransferRepository.save(scheduledTransferEntity);
        return scheduledTransferMapper.toResponse(savedScheduledTransferEntity);
    }

    @Override
    public ScheduledTransferResponse getById(Long id) {
       final ScheduledTransferEntity scheduledTransferEntity = scheduledTransferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        return scheduledTransferMapper.toResponse(scheduledTransferEntity);
    }

    @Override
    public PaginationResponse<ScheduledTransferResponse> getAll(final Pageable pageable) {
       final Page<ScheduledTransferEntity> scheduledTransferEntityPage = scheduledTransferRepository.findAll(pageable);

        final List<ScheduledTransferResponse> content = scheduledTransferEntityPage
                .getContent()
                .stream()
                .map(scheduledTransferMapper::toResponse)
                .toList();

        return new PaginationResponse<>(
                content,
                scheduledTransferEntityPage.getNumber(),
                scheduledTransferEntityPage.getSize(),
                scheduledTransferEntityPage.getTotalElements(),
                scheduledTransferEntityPage.getTotalPages()
        );
    }

    @Override
    public ScheduledTransferResponse update(
            final Long id,
            final ScheduledTransferRequest scheduledTransferRequest
    ) {
        final ScheduledTransferEntity scheduledTransferEntity = scheduledTransferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        scheduledTransferMapper.updateEntityFromRequest(scheduledTransferRequest, scheduledTransferEntity);

       final FeeResult feeResult = feeCalculator.calculate(
               scheduledTransferEntity.getAmount(),
               scheduledTransferEntity.getScheduleDate()
       );
        scheduledTransferEntity.setFee(feeResult.fee());

        final ScheduledTransferEntity savedScheduledTransferEntity = scheduledTransferRepository.save(scheduledTransferEntity);

        return scheduledTransferMapper.toResponse(savedScheduledTransferEntity);
    }

    @Override
    public void delete(Long id) {
        final ScheduledTransferEntity scheduledTransferEntity = scheduledTransferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

        scheduledTransferRepository.delete(scheduledTransferEntity);
    }
}