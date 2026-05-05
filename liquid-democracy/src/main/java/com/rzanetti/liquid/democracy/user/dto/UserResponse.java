package com.rzanetti.liquid.democracy.user.dto;

public record UserResponse(
        Long id,
        String name,
        String email
) {
}
