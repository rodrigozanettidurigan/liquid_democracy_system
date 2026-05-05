package com.rzanetti.liquid.democracy.delegation;

import com.rzanetti.liquid.democracy.enums.delegationstatus.DelegationStatus;
import com.rzanetti.liquid.democracy.topic.Topic;
import com.rzanetti.liquid.democracy.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    private User delegator;

    @ManyToOne(optional = false)
    private User delegate;

    @ManyToOne(optional = false)
    private Topic topic;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DelegationStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime revokedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}

