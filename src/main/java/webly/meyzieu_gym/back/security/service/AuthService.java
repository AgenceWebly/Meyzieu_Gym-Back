package webly.meyzieu_gym.back.security.service;

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
import org.springframework.stereotype.Service;

import webly.meyzieu_gym.back.common.exception.custom.RoleNotFoundException;
import webly.meyzieu_gym.back.common.exception.custom.UserAlreadyExistsException;
import webly.meyzieu_gym.back.security.jwt.JwtUtils;
import webly.meyzieu_gym.back.security.payload.request.LoginRequest;
import webly.meyzieu_gym.back.security.payload.request.SignupRequest;
import webly.meyzieu_gym.back.security.payload.response.MessageResponse;
import webly.meyzieu_gym.back.security.payload.response.UserInfoResponse;
import webly.meyzieu_gym.back.usermanagement.role.ERole;
import webly.meyzieu_gym.back.usermanagement.role.Role;
import webly.meyzieu_gym.back.usermanagement.role.RoleRepository;
import webly.meyzieu_gym.back.usermanagement.user.User;
import webly.meyzieu_gym.back.usermanagement.user.UserRepository;

@Service
public class AuthService {
    
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;
    private JwtUtils jwtUtils;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    public ResponseEntity<MessageResponse> registerUser(SignupRequest signUpRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            throw new UserAlreadyExistsException("Error: This email is already used!");
        }

        User user = new User(signUpRequest.getFirstname(),
                             signUpRequest.getLastname(),
                             signUpRequest.getEmail(),
                             encoder.encode(signUpRequest.getPassword()),
                             signUpRequest.getPhoneNumber(),
                             signUpRequest.getAddress()
                             );

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            addRoleToSet(roles, ERole.ROLE_USER);
            addRoleToSet(roles, ERole.ROLE_GUARDIAN);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        addRoleToSet(roles, ERole.ROLE_USER);
                        addRoleToSet(roles, ERole.ROLE_ADMIN);
                        break;
                    case "trainer":
                        addRoleToSet(roles, ERole.ROLE_USER);
                        addRoleToSet(roles, ERole.ROLE_TRAINER);
                        break;
                    default:
                        addRoleToSet(roles, ERole.ROLE_USER);
                        addRoleToSet(roles, ERole.ROLE_GUARDIAN);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    private void addRoleToSet(Set<Role> roles, ERole roleEnum) {
        Role role = roleRepository.findByName(roleEnum)
            .orElseThrow(() -> new RoleNotFoundException("Error: Role " + roleEnum + " is not found."));
        roles.add(role);
    }

    public ResponseEntity<UserInfoResponse> authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

        UserInfoResponse userInfoResponse = new UserInfoResponse(
            userDetails.getId(),
            userDetails.getEmail(),
            roles
        );

        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
            .body(userInfoResponse);
    }
}
