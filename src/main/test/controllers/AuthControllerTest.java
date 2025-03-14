package controllers;

import com.eci.Lab06_AuthService.controllers.AuthController;
import com.eci.Lab06_AuthService.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AuthControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    public AuthControllerTest() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void testRegisterUser() throws Exception {
        doNothing().when(userService).registerUser("testUser", "password123");

        mockMvc.perform(post("/auth/register")
                        .param("username", "testUser")
                        .param("password", "password123")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully!"));
    }

    @Test
    void testLogin_Success() throws Exception {
        when(userService.authenticateUser("testUser", "password123")).thenReturn(true);

        mockMvc.perform(post("/auth/login")
                        .param("username", "testUser")
                        .param("password", "password123")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string("Login successful!"));
    }

    @Test
    void testLogin_Failure() throws Exception {
        when(userService.authenticateUser("testUser", "wrongPassword")).thenReturn(false);

        mockMvc.perform(post("/auth/login")
                        .param("username", "testUser")
                        .param("password", "wrongPassword")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                .andExpect(content().string("Invalid credentials"));
    }
}
