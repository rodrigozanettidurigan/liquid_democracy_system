package com.rzanetti.liquid.democracy.vote;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rzanetti.liquid.democracy.enums.choices.VoteChoices;

import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    List<Vote> findByProposalId(Long proposalId);

    boolean existsByUserIdAndProposalId(Long userId, Long proposalId);

    long countByProposalIdAndChoice(Long proposalId, VoteChoices choice);
}
