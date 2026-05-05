package com.rzanetti.liquid.democracy.topic;

import com.rzanetti.liquid.democracy.exception.BusinessRuleException;
import com.rzanetti.liquid.democracy.exception.ResourceNotFoundException;
import com.rzanetti.liquid.democracy.topic.dto.CreateTopicRequest;
import com.rzanetti.liquid.democracy.topic.dto.TopicResponse;
import com.rzanetti.liquid.democracy.topic.dto.UpdateTopicRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TopicService {
    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Transactional
    public TopicResponse create(CreateTopicRequest dto) {
        if (topicRepository.existsByName(dto.name())) {
            throw new BusinessRuleException("Ja existe topico com este nome");
        }

        Topic topic = TopicMapper.toEntity(dto);
        Topic savedTopic = topicRepository.save(topic);

        return TopicMapper.toResponse(savedTopic);
    }

    @Transactional(readOnly = true)
    public List<TopicResponse> findAll() {
        return topicRepository.findAll()
                .stream()
                .map(TopicMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public TopicResponse findById(Long id) {
        return TopicMapper.toResponse(findEntityById(id));
    }

    @Transactional
    public TopicResponse update(Long id, UpdateTopicRequest dto) {
        Topic topic = findEntityById(id);

        if (topicRepository.existsByNameAndIdNot(dto.name(), id)) {
            throw new BusinessRuleException("Ja existe topico com este nome");
        }

        topic.update(dto.name());
        Topic updatedTopic = topicRepository.save(topic);

        return TopicMapper.toResponse(updatedTopic);
    }

    @Transactional
    public void delete(Long id) {
        if (!topicRepository.existsById(id)) {
            throw new ResourceNotFoundException("Topico nao encontrado");
        }

        topicRepository.deleteById(id);
    }

    private Topic findEntityById(Long id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topico nao encontrado"));
    }
}
