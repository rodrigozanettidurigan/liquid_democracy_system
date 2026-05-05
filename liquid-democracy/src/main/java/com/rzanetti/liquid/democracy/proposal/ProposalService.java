package com.rzanetti.liquid.democracy.proposal;

import com.rzanetti.liquid.democracy.enums.proposalstatus.ProposalStatus;
import com.rzanetti.liquid.democracy.exception.BusinessRuleException;
import com.rzanetti.liquid.democracy.exception.ResourceNotFoundException;
import com.rzanetti.liquid.democracy.proposal.dto.CreateProposalRequest;
import com.rzanetti.liquid.democracy.proposal.dto.ProposalResponse;
import com.rzanetti.liquid.democracy.proposal.dto.UpdateProposalRequest;
import com.rzanetti.liquid.democracy.topic.Topic;
import com.rzanetti.liquid.democracy.topic.TopicRepository;
import com.rzanetti.liquid.democracy.user.User;
import com.rzanetti.liquid.democracy.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProposalService {
    private final ProposalRepository proposalRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;

    public ProposalService(
            ProposalRepository proposalRepository,
            UserRepository userRepository,
            TopicRepository topicRepository) {
        this.proposalRepository = proposalRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
    }

    @Transactional
    public ProposalResponse create(CreateProposalRequest dto) {
        User createdBy = userRepository.findById(dto.createdByUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario criador nao encontrado"));
        Topic topic = topicRepository.findById(dto.topicId())
                .orElseThrow(() -> new ResourceNotFoundException("Topico nao encontrado"));

        Proposal proposal = ProposalMapper.toEntity(dto, createdBy, topic);
        Proposal savedProposal = proposalRepository.save(proposal);

        return ProposalMapper.toResponse(savedProposal);
    }

    @Transactional(readOnly = true)
    public List<ProposalResponse> findAll() {
        return proposalRepository.findAll()
                .stream()
                .map(ProposalMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProposalResponse findById(Long id) {
        return ProposalMapper.toResponse(findEntityById(id));
    }

    @Transactional
    public ProposalResponse update(Long id, UpdateProposalRequest dto) {
        Proposal proposal = findEntityById(id);
        Topic topic = topicRepository.findById(dto.topicId())
                .orElseThrow(() -> new ResourceNotFoundException("Topico nao encontrado"));

        proposal.update(dto.title(), dto.description(), topic, dto.votingDeadline());
        Proposal updatedProposal = proposalRepository.save(proposal);

        return ProposalMapper.toResponse(updatedProposal);
    }

    @Transactional
    public ProposalResponse open(Long id) {
        Proposal proposal = findEntityById(id);

        if (proposal.getStatus() != ProposalStatus.DRAFT) {
            throw new BusinessRuleException("Apenas propostas DRAFT podem ser abertas");
        }

        proposal.open();
        Proposal openedProposal = proposalRepository.save(proposal);

        return ProposalMapper.toResponse(openedProposal);
    }

    @Transactional
    public ProposalResponse close(Long id) {
        Proposal proposal = findEntityById(id);

        if (proposal.getStatus() != ProposalStatus.OPEN) {
            throw new BusinessRuleException("Apenas propostas OPEN podem ser fechadas");
        }

        proposal.close();
        Proposal closedProposal = proposalRepository.save(proposal);

        return ProposalMapper.toResponse(closedProposal);
    }

    @Transactional
    public void delete(Long id) {
        if (!proposalRepository.existsById(id)) {
            throw new ResourceNotFoundException("Proposta nao encontrada");
        }

        proposalRepository.deleteById(id);
    }

    private Proposal findEntityById(Long id) {
        return proposalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proposta nao encontrada"));
    }
}
