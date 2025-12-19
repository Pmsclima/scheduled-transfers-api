package com.pedro.scheduledtransfers.dto.response;

import java.util.List;

public record PaginationResponse<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages
) {}