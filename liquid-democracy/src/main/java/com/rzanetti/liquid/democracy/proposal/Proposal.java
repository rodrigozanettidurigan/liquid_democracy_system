package com.rzanetti.liquid.democracy.proposal;

import com.rzanetti.liquid.democracy.enums.proposalstatus.ProposalStatus;
import com.rzanetti.liquid.democracy.topic.Topic;
import com.rzanetti.liquid.democracy.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "proposals")
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(nullable = false, length = 5000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProposalStatus status = ProposalStatus.DRAFT;

    @ManyToOne(optional = false)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @ManyToOne(optional = false)
    @JoinColumn(name = "created_by_id", nullable = false)
    private User createdBy;

    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime votingDeadline;

    public Proposal(String title, String description, User createdBy, Topic topic, LocalDateTime votingDeadline) {
        this.title = title;
        this.description = description;
        this.createdBy = createdBy;
        this.topic = topic;
        this.votingDeadline = votingDeadline;
        this.status = ProposalStatus.DRAFT;
    }

    public void update(String title, String description, Topic topic, LocalDateTime votingDeadline) {
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.votingDeadline = votingDeadline;
    }

    public void open() {
        this.status = ProposalStatus.OPEN;
    }

    public void close() {
        this.status = ProposalStatus.CLOSED;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

}
