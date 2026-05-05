package com.rzanetti.liquid.democracy.delegation.dto;

import jakarta.validation.constraints.NotNull;

public record CreateDelegationRequest(
        @NotNull
        Long delegatorId,

        @NotNull
        Long delegateId,

        @NotNull
        Long topicId
) {
}
