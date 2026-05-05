package com.rzanetti.liquid.democracy.delegation;

import com.rzanetti.liquid.democracy.delegation.dto.CreateDelegationRequest;
import com.rzanetti.liquid.democracy.delegation.dto.DelegationResponse;
import com.rzanetti.liquid.democracy.enums.delegationstatus.DelegationStatus;
import com.rzanetti.liquid.democracy.exception.BusinessRuleException;
import com.rzanetti.liquid.democracy.exception.ResourceNotFoundException;
import com.rzanetti.liquid.democracy.topic.Topic;
import com.rzanetti.liquid.democracy.topic.TopicRepository;
import com.rzanetti.liquid.democracy.user.User;
import com.rzanetti.liquid.democracy.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DelegationService {
    private final DelegationRepository delegationRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;

    public DelegationService(
            DelegationRepository delegationRepository,
            UserRepository userRepository,
            TopicRepository topicRepository) {
        this.delegationRepository = delegationRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
    }

    @Transactional
    public DelegationResponse create(CreateDelegationRequest dto) {
        if (dto.delegatorId().equals(dto.delegateId())) {
            throw new BusinessRuleException("Usuario nao pode delegar para si mesmo");
        }

        User delegator = userRepository.findById(dto.delegatorId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario delegador nao encontrado"));
        User delegate = userRepository.findById(dto.delegateId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario delegado nao encontrado"));
        Topic topic = topicRepository.findById(dto.topicId())
                .orElseThrow(() -> new ResourceNotFoundException("Topico nao encontrado"));

        Delegation delegation = DelegationMapper.toEntity(delegator, delegate, topic);
        Delegation savedDelegation = delegationRepository.save(delegation);

        return DelegationMapper.toResponse(savedDelegation);
    }

    @Transactional(readOnly = true)
    public List<DelegationResponse> findAll() {
        return delegationRepository.findAll()
                .stream()
                .map(DelegationMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public DelegationResponse findById(Long id) {
        return DelegationMapper.toResponse(findEntityById(id));
    }

    @Transactional
    public DelegationResponse revoke(Long id) {
        Delegation delegation = findEntityById(id);

        if (delegation.getStatus() != DelegationStatus.ACTIVE) {
            throw new BusinessRuleException("Apenas delegacoes ACTIVE podem ser revogadas");
        }

        delegation.revoke();
        Delegation revokedDelegation = delegationRepository.save(delegation);

        return DelegationMapper.toResponse(revokedDelegation);
    }

    @Transactional
    public void delete(Long id) {
        if (!delegationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Delegacao nao encontrada");
        }

        delegationRepository.deleteById(id);
    }

    private Delegation findEntityById(Long id) {
        return delegationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Delegacao nao encontrada"));
    }
}
