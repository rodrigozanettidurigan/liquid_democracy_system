package com.rzanetti.liquid.democracy.proposal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateProposalRequest(
        @NotBlank
        String title,

        @NotBlank
        String description,

        @NotNull
        Long createdByUserId,

        @NotNull
        Long topicId,

        LocalDateTime votingDeadline
) {
}
