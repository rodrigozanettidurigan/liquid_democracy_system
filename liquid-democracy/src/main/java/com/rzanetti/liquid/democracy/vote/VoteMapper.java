package com.rzanetti.liquid.democracy.vote;

import com.rzanetti.liquid.democracy.proposal.Proposal;
import com.rzanetti.liquid.democracy.user.User;
import com.rzanetti.liquid.democracy.vote.dto.CreateVoteRequest;
import com.rzanetti.liquid.democracy.vote.dto.VoteResponse;

public class VoteMapper {
    private VoteMapper() {
    }

    public static Vote toEntity(CreateVoteRequest dto, User user, Proposal proposal) {
        return new Vote(user, proposal, dto.choice());
    }

    public static VoteResponse toResponse(Vote vote) {
        return new VoteResponse(
                vote.getId(),
                vote.getUser().getId(),
                vote.getUser().getName(),
                vote.getProposal().getId(),
                vote.getProposal().getTitle(),
                vote.getChoice(),
                vote.getCreatedAt()
        );
    }
}
