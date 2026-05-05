package com.rzanetti.liquid.democracy.vote;

import com.rzanetti.liquid.democracy.enums.proposalstatus.ProposalStatus;
import com.rzanetti.liquid.democracy.exception.BusinessRuleException;
import com.rzanetti.liquid.democracy.exception.ResourceNotFoundException;
import com.rzanetti.liquid.democracy.proposal.Proposal;
import com.rzanetti.liquid.democracy.proposal.ProposalRepository;
import com.rzanetti.liquid.democracy.user.User;
import com.rzanetti.liquid.democracy.user.UserRepository;
import com.rzanetti.liquid.democracy.vote.dto.CreateVoteRequest;
import com.rzanetti.liquid.democracy.vote.dto.VoteResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final ProposalRepository proposalRepository;
    private final UserRepository userRepository;

    public VoteService(
            VoteRepository voteRepository,
            ProposalRepository proposalRepository,
            UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.proposalRepository = proposalRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public VoteResponse create(Long proposalId, CreateVoteRequest dto) {
        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new ResourceNotFoundException("Proposta nao encontrada"));

        if (proposal.getStatus() != ProposalStatus.OPEN) {
            throw new BusinessRuleException("Nao e permitido votar em proposta que nao esteja OPEN");
        }

        User user = userRepository.findById(dto.userId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado"));

        if (voteRepository.existsByUserIdAndProposalId(user.getId(), proposal.getId())) {
            throw new BusinessRuleException("Usuario ja votou nesta proposta");
        }

        Vote vote = VoteMapper.toEntity(dto, user, proposal);
        Vote savedVote = voteRepository.save(vote);

        return VoteMapper.toResponse(savedVote);
    }

    @Transactional(readOnly = true)
    public List<VoteResponse> findByProposalId(Long proposalId) {
        if (!proposalRepository.existsById(proposalId)) {
            throw new ResourceNotFoundException("Proposta nao encontrada");
        }

        return voteRepository.findByProposalId(proposalId)
                .stream()
                .map(VoteMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public VoteResponse findById(Long id) {
        return VoteMapper.toResponse(findEntityById(id));
    }

    @Transactional
    public void delete(Long id) {
        if (!voteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Voto nao encontrado");
        }

        voteRepository.deleteById(id);
    }

    private Vote findEntityById(Long id) {
        return voteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voto nao encontrado"));
    }
}
