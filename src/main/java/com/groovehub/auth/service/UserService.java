package com.groovehub.auth.service;

import com.groovehub.auth.dto.UserDTO;
import com.groovehub.auth.exception.UserAlreadyExistsException;
import com.groovehub.auth.exception.UserNotFoundException;
import com.groovehub.auth.exception.WrongPasswordException;
import com.groovehub.auth.model.User;
import com.groovehub.auth.repository.UserRepository;
import com.groovehub.auth.util.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Key jwtKey;
    private final long jwtExpirationMs = 3600000;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        // this.jwtUtil = jwtUtil;
        this.jwtKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

    }

    public User createUser(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username already exists: " + userDTO.getUsername());
        }

        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email already exists: " + userDTO.getEmail());
        }

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    private String generateJwtToken(User user) {
        return Jwts.builder().subject(user.getUsername())
                .claim("role", user.getRole().toString()).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(jwtKey)
                .compact();
    }

    public UserDTO authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new WrongPasswordException("Invalid password");
        }
        UserDTO userDTO =  convertToDTO(user);
        userDTO.setJwtToken(generateJwtToken(user));
        return userDTO;
    }

    public UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User convertToEntity(UserDTO dto) {
        User user = modelMapper.map(dto, User.class);
        if (dto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        return user;
    }
}
