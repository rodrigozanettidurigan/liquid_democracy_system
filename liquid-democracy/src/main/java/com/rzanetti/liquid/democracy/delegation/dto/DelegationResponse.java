package com.rzanetti.liquid.democracy.delegation.dto;

import com.rzanetti.liquid.democracy.enums.delegationstatus.DelegationStatus;

import java.time.LocalDateTime;

public record DelegationResponse(
        Long id,
        Long delegatorId,
        String delegatorName,
        Long delegateId,
        String delegateName,
        Long topicId,
        String topicName,
        DelegationStatus status,
        LocalDateTime createdAt,
        LocalDateTime revokedAt
) {
}
