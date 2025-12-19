package com.pedro.scheduledtransfers.persistence.repository;

import com.pedro.scheduledtransfers.persistence.entity.ScheduledTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledTransferRepository extends JpaRepository<ScheduledTransferEntity, Long> {}