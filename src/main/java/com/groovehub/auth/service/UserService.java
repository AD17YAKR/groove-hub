package com.groovehub.auth.service;

import com.groovehub.auth.dto.UserDTO;
import com.groovehub.auth.exception.UserAlreadyExistsException;
import com.groovehub.auth.exception.UserNotFoundException;
import com.groovehub.auth.exception.WrongPasswordException;
import com.groovehub.auth.mapper.UserMapper;
import com.groovehub.auth.model.User;
import com.groovehub.auth.repository.UserRepository;
import com.groovehub.auth.util.JwtUtils;
import com.groovehub.auth.util.Role;
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