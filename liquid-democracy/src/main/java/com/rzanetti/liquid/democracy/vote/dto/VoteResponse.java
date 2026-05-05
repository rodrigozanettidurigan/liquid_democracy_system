package com.rzanetti.liquid.democracy.vote.dto;

import com.rzanetti.liquid.democracy.enums.choices.VoteChoices;

import java.time.LocalDateTime;

public record VoteResponse(
        Long id,
        Long userId,
        String userName,
        Long proposalId,
        String proposalTitle,
        VoteChoices choice,
        LocalDateTime createdAt
) {
}
