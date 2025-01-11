package com.groovehub.auth;

import com.groovehub.auth.controller.UserController;
import com.groovehub.auth.dto.UserDTO;
import com.groovehub.auth.model.User;
import com.groovehub.auth.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;
    private UserDTO userDTO;
    private User user;

    @BeforeEach
    void setUp() {
        userDTO = new UserDTO();
        userDTO.setUsername("testUser");
        userDTO.setPassword("testPassword");
        user = new User();
        user.setUserId(1L);
        user.setUsername("testUser");
    }

    @Test
    void createUser_Success() {
        when(userService.createUser(any(UserDTO.class))).thenReturn(user);
        ResponseEntity<User> response = userController.createUser(userDTO);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(user, response.getBody());
        assertEquals("testUser", response.getBody().getUsername());
    }

    @Test
    void loginUser_Success() {

        when(userService.authenticateUser(userDTO.getUsername(), userDTO.getPassword()))
                .thenReturn(user);
        ResponseEntity<?> response = userController.loginUser(userDTO);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(user, response.getBody());
    }

    @Test
    void loginUser_InvalidCredentials() {

        when(userService.authenticateUser(userDTO.getUsername(), userDTO.getPassword()))
                .thenReturn(null);
        ResponseEntity<?> response = userController.loginUser(userDTO);
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertNull(response.getBody());
    }
}
