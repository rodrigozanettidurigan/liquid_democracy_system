package com.rzanetti.liquid.democracy.topic.dto;

import java.time.LocalDateTime;

public record TopicResponse(
        Long id,
        String name,
        LocalDateTime createdAt
) {
}
