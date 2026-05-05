package com.rzanetti.liquid.democracy.user;

import com.rzanetti.liquid.democracy.exception.BusinessRuleException;
import com.rzanetti.liquid.democracy.exception.ResourceNotFoundException;
import com.rzanetti.liquid.democracy.user.dto.CreateUserRequest;
import com.rzanetti.liquid.democracy.user.dto.UpdateUserRequest;
import com.rzanetti.liquid.democracy.user.dto.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse create(CreateUserRequest dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new BusinessRuleException("Ja existe usuario com este e-mail");
        }

        User user = UserMapper.toEntity(dto);
        User savedUser = userRepository.save(user);

        return UserMapper.toResponse(savedUser);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        return UserMapper.toResponse(findEntityById(id));
    }

    @Transactional
    public UserResponse update(Long id, UpdateUserRequest dto) {
        User user = findEntityById(id);

        if (userRepository.existsByEmailAndIdNot(dto.email(), id)) {
            throw new BusinessRuleException("Ja existe usuario com este e-mail");
        }

        user.update(dto.name(), dto.email());
        User updatedUser = userRepository.save(user);

        return UserMapper.toResponse(updatedUser);
    }

    @Transactional
    public void delete(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Usuario nao encontrado");
        }

        userRepository.deleteById(id);
    }

    private User findEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado"));
    }
}
