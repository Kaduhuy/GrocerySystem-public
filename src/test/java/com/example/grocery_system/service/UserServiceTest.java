package com.example.grocery_system.service;

import com.example.grocery_system.model.Users;
import com.example.grocery_system.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;
    
    @Mock
    PasswordEncoder passwordEncoder;

    @Captor
    ArgumentCaptor<Users> userCaptor;

    @Spy
    @InjectMocks
    UserService userService;
    
    @Test
    public void shouldRegisterUser(){
        //Arrange
        Users user = new Users(null, "test", "test@example.com", "test");

        doReturn(user.getPassword()).when(passwordEncoder).encode(passwordEncoder.encode(user.getPassword()));

        //Act
        userService.registerUser(user.getName(), user.getEmail(), user.getPassword());

        //Verify & ArgumentCaptor
        verify(userRepository, times(1)).save(userCaptor.capture());
        verify(passwordEncoder, times(1)).encode(user.getPassword());

        Users registeredUser = userCaptor.getValue();

        //Assert
        assertEquals(user.getName(), registeredUser.getName());
        assertEquals(user.getEmail(), registeredUser.getEmail());
        assertEquals(user.getPassword(), registeredUser.getPassword());
    }

    @Test
    public void shouldThrowExceptionWhenUserEmailAlreadyExists(){
        //Arrange
        Users user = new Users(null, "test", "test@example.com", "test");
        List<Users> users = new ArrayList<>();
        users.add(user);

        doReturn(Optional.of(users)).when(userRepository).findByEmail(user.getEmail());

        //Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(user.getName(),user.getEmail(),user.getPassword());
        });

        String expectedMessage = "User email already exists: test@example.com";
        String actualMessaage = exception.getMessage();

        assertEquals(expectedMessage, actualMessaage);
    }
}
