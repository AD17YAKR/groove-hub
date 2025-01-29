package com.promptverse.auth.service;

import com.promptverse.auth.dto.UserDTO;
import com.promptverse.auth.exception.UserAlreadyExistsException;
import com.promptverse.auth.exception.UserNotFoundException;
import com.promptverse.auth.exception.WrongPasswordException;
import com.promptverse.auth.mapper.UserMapper;
import com.promptverse.auth.model.User;
import com.promptverse.auth.repository.UserRepository;
import com.promptverse.auth.utils.JwtUtils;
import com.promptverse.auth.utils.Role;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jwtUtils = jwtUtils;
    }

    @Transactional
    public User createUser(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username already exists: " + userDTO.getUsername());
        }

        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email already exists: " + userDTO.getEmail());
        }

        User user = userMapper.convertToEntity(userDTO);
        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public UserDTO authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new WrongPasswordException("Invalid password");
        }

        UserDTO userDTO = userMapper.convertToDTO(user);
        userDTO.setJwtToken(jwtUtils.generateJwtToken(userDTO));
        userDTO.setPassword(null);
        return userDTO;
    }
}