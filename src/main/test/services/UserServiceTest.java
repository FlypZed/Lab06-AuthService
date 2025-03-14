package services;

import com.eci.Lab06_AuthService.models.User;
import com.eci.Lab06_AuthService.repositories.UserRepository;
import com.eci.Lab06_AuthService.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        String username = "testUser";
        String rawPassword = "password123";
        String hashedPassword = "hashedPassword";

        when(passwordEncoder.encode(rawPassword)).thenReturn(hashedPassword);

        userService.registerUser(username, rawPassword);

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testAuthenticateUser_Success() {
        String username = "testUser";
        String rawPassword = "password123";
        String hashedPassword = "hashedPassword";

        User user = new User(username, hashedPassword);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(rawPassword, hashedPassword)).thenReturn(true);

        assertTrue(userService.authenticateUser(username, rawPassword));
    }

    @Test
    void testAuthenticateUser_Failure() {
        String username = "testUser";
        String rawPassword = "wrongPassword";

        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        assertFalse(userService.authenticateUser(username, rawPassword));
    }
}
