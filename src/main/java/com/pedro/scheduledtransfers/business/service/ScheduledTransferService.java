package com.pedro.scheduledtransfers.business.service;

import com.pedro.scheduledtransfers.business.fee.FeeCalculator;
import com.pedro.scheduledtransfers.business.fee.FeeResult;
import com.pedro.scheduledtransfers.dto.request.ScheduledTransferRequest;
import com.pedro.scheduledtransfers.dto.response.ScheduledTransferResponse;
import com.pedro.scheduledtransfers.mapper.ScheduledTransferMapper;
import com.pedro.scheduledtransfers.persistence.entity.ScheduledTransferEntity;
import com.pedro.scheduledtransfers.persistence.repository.ScheduledTransferRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        ScheduledTransferEntity savedScheduledTransferEntity =
                scheduledTransferRepository.save(scheduledTransferEntity);
        return scheduledTransferMapper.toResponse(savedScheduledTransferEntity);
    }
}