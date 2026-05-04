package com.rzanetti.liquid.democracy.vote;

import com.rzanetti.liquid.democracy.proposal.Proposal;

import java.time.LocalDateTime;

public class Vote {
    private Long id;
    private String user;
    private Proposal proposal;
    private String choice;
    private LocalDateTime createdAt;
}

