package webly.meyzieu_gym.back.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import webly.meyzieu_gym.back.security.payload.request.LoginRequest;
import webly.meyzieu_gym.back.security.payload.request.SignupRequest;
import webly.meyzieu_gym.back.security.payload.response.MessageResponse;
import webly.meyzieu_gym.back.security.payload.response.UserInfoResponse;
import webly.meyzieu_gym.back.security.service.AuthService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }

    @PostMapping("/signin")
    public ResponseEntity<UserInfoResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/signout")
    public ResponseEntity<MessageResponse> logoutUser() {
        return authService.logout();
    }
}
