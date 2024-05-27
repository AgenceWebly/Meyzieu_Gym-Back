package webly.meyzieu_gym.back.security.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import webly.meyzieu_gym.back.security.jwt.JwtUtils;
import webly.meyzieu_gym.back.security.payload.request.LoginRequest;
import webly.meyzieu_gym.back.security.payload.request.SignupRequest;
import webly.meyzieu_gym.back.security.payload.response.MessageResponse;
import webly.meyzieu_gym.back.security.payload.response.UserInfoResponse;
import webly.meyzieu_gym.back.security.service.UserDetailsImpl;
import webly.meyzieu_gym.back.usermanagement.role.ERole;
import webly.meyzieu_gym.back.usermanagement.role.Role;
import webly.meyzieu_gym.back.usermanagement.role.RoleRepository;
import webly.meyzieu_gym.back.usermanagement.user.User;
import webly.meyzieu_gym.back.usermanagement.user.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    AuthenticationManager authenticationManager;
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder encoder;
    JwtUtils jwtUtils;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

        // Check if the email is already in use
        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
        // Return a bad request response with an error message
        return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account with provided details and encoded password
        User user = new User(signUpRequest.getFirstname(),
                            signUpRequest.getLastname(),
                            signUpRequest.getEmail(),
                            encoder.encode(signUpRequest.getPassword()),
                            signUpRequest.getPhoneNumber(),
                            signUpRequest.getAddress()
                            );

        // Initialize a set to store the roles
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        // Assign roles to the user based on the provided roles in the signup request
        if (strRoles == null) {
            // Default role if no specific role is provided
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            } else {
            // Assign roles based on the provided role names
            strRoles.forEach(role -> {
                switch (role) {
                case "admin":
                Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(adminRole);

                break;
                case "mod":
                Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(modRole);

                break;
                default:
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


    @PostMapping("/signin")
    public ResponseEntity<UserInfoResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
    
        // Authenticate the user with the provided email and password
        Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        // Set the authenticated user in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Get the authenticated user's details
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Generate a JWT and set it in a cookie
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        // Collect user roles from the authorities
        List<String> roles = userDetails.getAuthorities().stream()
                            .map(item -> item.getAuthority())
                            .collect(Collectors.toList());

        // Create response with user details
        UserInfoResponse userInfoResponse = new UserInfoResponse(
                                                userDetails.getId(),
                                                userDetails.getEmail(),
                                                roles);

        // Return response with JWT cookie set in the headers and user info in the body
        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
            .body(userInfoResponse);
    }

}
