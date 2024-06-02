package webly.meyzieu_gym.back.security.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import webly.meyzieu_gym.back.security.TestSecurityConfig;
import webly.meyzieu_gym.back.security.payload.request.LoginRequest;
import webly.meyzieu_gym.back.security.payload.request.SignupRequest;
import webly.meyzieu_gym.back.security.payload.response.MessageResponse;
import webly.meyzieu_gym.back.security.payload.response.UserInfoResponse;
import webly.meyzieu_gym.back.security.service.AuthService;

import java.util.List;
import java.util.Set;

@WebMvcTest(AuthController.class)
@Import(TestSecurityConfig.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Success() throws Exception {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setFirstname("Ben");
        signupRequest.setLastname("Dover");
        signupRequest.setEmail("trainer@gmail.com");
        signupRequest.setPassword("password");
        signupRequest.setPhoneNumber("01 05 09 15 65");
        signupRequest.setAddress("123 Elm Street");
        signupRequest.setRole(Set.of("trainer"));

        when(authService.registerUser(any(SignupRequest.class)))
                .thenReturn(ResponseEntity.ok(new MessageResponse("User registered successfully!")));

        mockMvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signupRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void testRegisterUser_EmailAlreadyExists() throws Exception {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setFirstname("Ben");
        signupRequest.setLastname("Dover");
        signupRequest.setEmail("trainer@gmail.com");
        signupRequest.setPassword("password");
        signupRequest.setPhoneNumber("01 05 09 15 65");
        signupRequest.setAddress("123 Elm Street");
        signupRequest.setRole(Set.of("trainer"));

        when(authService.registerUser(any(SignupRequest.class)))
                .thenReturn(ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!")));

        mockMvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signupRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testAuthenticateUser_Success() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("trainer@gmail.com");
        loginRequest.setPassword("password");

        // Create a UserInfoResponse object to match the expected return type
        UserInfoResponse userInfoResponse = new UserInfoResponse(
                (long) 1, 
                loginRequest.getEmail(), 
                List.of("ROLE_TRAINER")
        );

        when(authService.authenticateUser(any(LoginRequest.class)))
                .thenReturn(ResponseEntity.ok(userInfoResponse));

        mockMvc.perform(post("/api/auth/signin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk());
    }

}