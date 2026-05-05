package com.rzanetti.liquid.democracy.delegation;

import com.rzanetti.liquid.democracy.enums.delegationstatus.DelegationStatus;
import com.rzanetti.liquid.democracy.topic.Topic;
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
@Table(name = "delegations")
public class Delegation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "delegator_id", nullable = false)
    private User delegator;

    @ManyToOne(optional = false)
    @JoinColumn(name = "delegate_id", nullable = false)
    private User delegate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DelegationStatus status = DelegationStatus.ACTIVE;

    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime revokedAt;

    public Delegation(User delegator, User delegate, Topic topic) {
        this.delegator = delegator;
        this.delegate = delegate;
        this.topic = topic;
        this.status = DelegationStatus.ACTIVE;
    }

    public void revoke() {
        this.status = DelegationStatus.REVOKED;
        this.revokedAt = LocalDateTime.now();
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
