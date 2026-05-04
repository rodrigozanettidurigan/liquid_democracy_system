package com.rzanetti.liquid.democracy.delegation;

import com.rzanetti.liquid.democracy.enums.delegationstatus.DelegationStatus;
import com.rzanetti.liquid.democracy.topic.Topic;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Id
@Table(name = "delegation")
public class Delegation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String delegator;
    private String delegate;

    @Embedded
    private Topic topic;

    @Enumerated(EnumType.STRING)
    private DelegationStatus status; //Bolean só se precisar de 3 estados- true false null
    private LocalDateTime createdAt;
    private LocalDateTime revokedAt;
}

