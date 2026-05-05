package com.rzanetti.liquid.democracy.vote.dto;

import com.rzanetti.liquid.democracy.enums.choices.VoteChoices;
import jakarta.validation.constraints.NotNull;

public record CreateVoteRequest(
        @NotNull
        Long userId,

        @NotNull
        VoteChoices choice
) {
}
