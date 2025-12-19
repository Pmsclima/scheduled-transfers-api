package com.pedro.scheduledtransfers.mapper;

import com.pedro.scheduledtransfers.dto.request.ScheduledTransferRequest;
import com.pedro.scheduledtransfers.dto.response.ScheduledTransferResponse;
import com.pedro.scheduledtransfers.persistence.entity.ScheduledTransferEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ScheduledTransferMapper {

    ScheduledTransferEntity toEntity(ScheduledTransferRequest scheduledTransferRequest);

    ScheduledTransferResponse toResponse(ScheduledTransferEntity scheduledTransferEntity);

    void updateEntityFromRequest(
            ScheduledTransferRequest scheduledTransferRequest,
            @MappingTarget ScheduledTransferEntity scheduledTransferEntity
    );
}