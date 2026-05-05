package com.rzanetti.liquid.democracy.vote;

import com.rzanetti.liquid.democracy.enums.choices.VoteChoices;
import com.rzanetti.liquid.democracy.proposal.Proposal;
import com.rzanetti.liquid.democracy.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "votes",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "proposal_id" })
        })//evita duplicidade
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "proposal_id", nullable = false)
    private Proposal proposal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VoteChoices choice;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Vote(User user, Proposal proposal, VoteChoices choice) {
        this.user = user;
        this.proposal = proposal;
        this.choice = choice;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
