package com.rzanetti.liquid.democracy.topic;

import com.rzanetti.liquid.democracy.topic.dto.CreateTopicRequest;
import com.rzanetti.liquid.democracy.topic.dto.TopicResponse;

public class TopicMapper {
    private TopicMapper() {
    }

    public static Topic toEntity(CreateTopicRequest dto) {
        return new Topic(dto.name());
    }

    public static TopicResponse toResponse(Topic topic) {
        return new TopicResponse(
                topic.getId(),
                topic.getName(),
                topic.getCreatedAt()
        );
    }
}
