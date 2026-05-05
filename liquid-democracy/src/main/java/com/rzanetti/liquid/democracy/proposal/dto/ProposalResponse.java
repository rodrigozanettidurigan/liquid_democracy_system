package com.rzanetti.liquid.democracy.proposal.dto;

import com.rzanetti.liquid.democracy.enums.proposalstatus.ProposalStatus;

import java.time.LocalDateTime;

public record ProposalResponse(
        Long id,
        String title,
        String description,
        ProposalStatus status,
        Long createdByUserId,
        String createdByName,
        Long topicId,
        String topicName,
        LocalDateTime createdAt,
        LocalDateTime votingDeadline
) {
}
