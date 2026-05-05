package com.rzanetti.liquid.democracy.proposal;

import com.rzanetti.liquid.democracy.proposal.dto.CreateProposalRequest;
import com.rzanetti.liquid.democracy.proposal.dto.ProposalResponse;
import com.rzanetti.liquid.democracy.topic.Topic;
import com.rzanetti.liquid.democracy.user.User;

public class ProposalMapper {
    private ProposalMapper() {
    }

    public static Proposal toEntity(CreateProposalRequest dto, User createdBy, Topic topic) {
        return new Proposal(
                dto.title(),
                dto.description(),
                createdBy,
                topic,
                dto.votingDeadline()
        );
    }

    public static ProposalResponse toResponse(Proposal proposal) {
        return new ProposalResponse(
                proposal.getId(),
                proposal.getTitle(),
                proposal.getDescription(),
                proposal.getStatus(),
                proposal.getCreatedBy().getId(),
                proposal.getCreatedBy().getName(),
                proposal.getTopic().getId(),
                proposal.getTopic().getName(),
                proposal.getCreatedAt(),
                proposal.getVotingDeadline()
        );
    }
}
