package com.rzanetti.liquid.democracy.topic.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateTopicRequest(
        @NotBlank
        String name
) {
}
