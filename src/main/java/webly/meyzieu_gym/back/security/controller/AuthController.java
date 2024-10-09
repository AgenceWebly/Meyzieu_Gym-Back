package webly.meyzieu_gym.back.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import webly.meyzieu_gym.back.security.payload.request.LoginRequest;
import webly.meyzieu_gym.back.security.payload.request.ResetPasswordRequest;
import webly.meyzieu_gym.back.security.payload.request.SignupRequest;
import webly.meyzieu_gym.back.security.payload.response.MessageResponse;
import webly.meyzieu_gym.back.security.payload.response.UserInfoResponse;
import webly.meyzieu_gym.back.security.service.AuthService;
import webly.meyzieu_gym.back.security.service.PasswordResetService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final PasswordResetService passwordResetService;

    public AuthController(AuthService authService, PasswordResetService passwordResetService) {
        this.authService = authService;
        this.passwordResetService = passwordResetService;
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

    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@RequestParam String email) {
        passwordResetService.initiatePasswordReset(email);
        return ResponseEntity.ok().build();  // Toujours retourner OK même si l'email n'existe pas
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@RequestBody ResetPasswordRequest request) {
        if (passwordResetService.isTokenValid(request.getToken())) {
            passwordResetService.resetPassword(request.getToken(), request.getNewPassword());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
