package com.dryunkaaa.userservice.service;

import java.util.List;
import java.util.UUID;

import com.dryunkaaa.userservice.dto.CreateUserDTO;
import com.dryunkaaa.userservice.dto.UserDTO;
import com.dryunkaaa.userservice.exception.DuplicateUserException;
import com.dryunkaaa.userservice.exception.UserNotFoundException;
import com.dryunkaaa.userservice.mapper.UserMapper;
import com.dryunkaaa.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::from)
                .toList();
    }

    public UserDTO findById(UUID id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        log.info("User with id [{}] successfully found", id);
        return userMapper.from(user);
    }

    @Transactional
    public void softDelete(UUID id) {
        userRepository.softDelete(id);
        log.info("User with id [{}] successfully deleted", id);
    }

    @Transactional
    public UserDTO create(CreateUserDTO createUserDTO) {
        var existingUser = userRepository.findByEmail(createUserDTO.email());
        if (existingUser.isPresent()) {
            throw new DuplicateUserException(createUserDTO.email());
        }

        var savedUser = userRepository.save(userMapper.to(createUserDTO));
        log.info("Created user with email '{}' [{}]", savedUser.getEmail(), savedUser.getId());

        return userMapper.from(savedUser);
    }

}
