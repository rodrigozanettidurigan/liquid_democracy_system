package com.rzanetti.liquid.democracy.result.dto;

public record VotingResultResponse(
        Long proposalId,
        String title,
        long yesVotes,
        long noVotes,
        long abstentions,
        long totalVotes
) {
}
