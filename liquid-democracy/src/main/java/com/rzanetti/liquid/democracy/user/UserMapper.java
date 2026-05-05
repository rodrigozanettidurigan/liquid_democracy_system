package com.rzanetti.liquid.democracy.user;

import com.rzanetti.liquid.democracy.user.dto.CreateUserRequest;
import com.rzanetti.liquid.democracy.user.dto.UserResponse;

public class UserMapper {

    private UserMapper() {
    }

    public static User toEntity(CreateUserRequest dto) {
        return new User(
                dto.name(),
                dto.email(),
                dto.password()
        );
    }

    public static UserResponse toResponse(User user)  {
            return new UserResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail()
            );
    }
}
