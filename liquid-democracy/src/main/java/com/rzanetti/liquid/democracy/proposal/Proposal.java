package com.rzanetti.liquid.democracy.proposal;

import com.rzanetti.liquid.democracy.enums.proposalstatus.ProposalStatus;
import com.rzanetti.liquid.democracy.topic.Topic;
import com.rzanetti.liquid.democracy.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "proposal")
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProposalStatus status = ProposalStatus.DRAFT;

    @ManyToOne(optional = false)//Muitas propostas podem pertencer ao mesmo topico, e optional nao pode existir proposta s/topico
    private Topic topic;

    @ManyToOne
    private User createdBy;

    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime votingDeadline;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

}

