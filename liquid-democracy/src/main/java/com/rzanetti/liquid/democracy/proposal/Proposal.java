package com.rzanetti.liquid.democracy.proposal;

import com.rzanetti.liquid.democracy.enums.proposalstatus.ProposalStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "proposal")
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @Enumerated(EnumType.STRING)
    private ProposalStatus status;

    @NotNull
    private LocalDateTime createdAt;
    private LocalDateTime votingDeadLine;
}

