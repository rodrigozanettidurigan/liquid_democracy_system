package com.rzanetti.liquid.democracy.result;

import com.rzanetti.liquid.democracy.enums.choices.VoteChoices;
import com.rzanetti.liquid.democracy.exception.ResourceNotFoundException;
import com.rzanetti.liquid.democracy.proposal.Proposal;
import com.rzanetti.liquid.democracy.proposal.ProposalRepository;
import com.rzanetti.liquid.democracy.result.dto.VotingResultResponse;
import com.rzanetti.liquid.democracy.vote.VoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VotingResultService {
    private final ProposalRepository proposalRepository;
    private final VoteRepository voteRepository;

    public VotingResultService(ProposalRepository proposalRepository, VoteRepository voteRepository) {
        this.proposalRepository = proposalRepository;
        this.voteRepository = voteRepository;
    }

    @Transactional(readOnly = true)
    public VotingResultResponse getResult(Long proposalId) {
        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new ResourceNotFoundException("Proposta nao encontrada"));

        long yesVotes = voteRepository.countByProposalIdAndChoice(proposalId, VoteChoices.YES);
        long noVotes = voteRepository.countByProposalIdAndChoice(proposalId, VoteChoices.NO);
        long abstentions = voteRepository.countByProposalIdAndChoice(proposalId, VoteChoices.ABSTAIN);
        long totalVotes = yesVotes + noVotes + abstentions;

        return new VotingResultResponse(
                proposal.getId(),
                proposal.getTitle(),
                yesVotes,
                noVotes,
                abstentions,
                totalVotes
        );
    }
}
