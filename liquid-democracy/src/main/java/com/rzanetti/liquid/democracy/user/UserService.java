package com.rzanetti.liquid.democracy.user;

import com.rzanetti.liquid.democracy.user.dto.CreateUserRequest;
import com.rzanetti.liquid.democracy.user.dto.UpdateUserRequest;
import com.rzanetti.liquid.democracy.user.dto.UserResponse;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse create(CreateUserRequest dto) {
        User user = UserMapper.toEntity(dto);

        User savedUser = userRepository.save(user);

        return UserMapper.toResponse(savedUser);
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return UserMapper.toResponse(user);
    }
    public UserResponse update(Long id, UpdateUserRequest dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        user.update(dto.name(), dto.email());

        User updatedUser = userRepository.save(user);

        return UserMapper.toResponse(updatedUser);
    }
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        userRepository.deleteById(id);
    }

}

