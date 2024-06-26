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
import webly.meyzieu_gym.back.emailmanagement.EmailConfService;
import webly.meyzieu_gym.back.security.jwt.JwtUtils;
import webly.meyzieu_gym.back.security.payload.request.LoginRequest;
import webly.meyzieu_gym.back.security.payload.request.SignupRequest;
import webly.meyzieu_gym.back.security.payload.response.MessageResponse;
import webly.meyzieu_gym.back.security.payload.response.UserInfoResponse;
import webly.meyzieu_gym.back.usermanagement.role.ERole;
import webly.meyzieu_gym.back.usermanagement.role.Role;
import webly.meyzieu_gym.back.usermanagement.role.RoleRepository;
import webly.meyzieu_gym.back.usermanagement.user.entity.User;
import webly.meyzieu_gym.back.usermanagement.user.repository.UserRepository;

@Service
public class AuthService {
    
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;
    private JwtUtils jwtUtils;
    private EmailConfService emailConfService;

    public AuthService(
            AuthenticationManager authenticationManager, 
            UserRepository userRepository, 
            RoleRepository roleRepository, 
            PasswordEncoder encoder, 
            JwtUtils jwtUtils,
            EmailConfService emailConfService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
        this.emailConfService = emailConfService;
    }

    public ResponseEntity<MessageResponse> registerUser(SignupRequest signUpRequest) {
        if (Boolean.TRUE.equals(userRepository.existsByEmail(signUpRequest.getEmail()))) {
            throw new UserAlreadyExistsException("Erreur: cet email est déjà utilisé par un autre utilisateur");
        }

        User user = new User(signUpRequest.getFirstname(),
                             signUpRequest.getLastname(),
                             signUpRequest.getEmail(),
                             encoder.encode(signUpRequest.getPassword()),
                             signUpRequest.getPhoneNumber(),
                             signUpRequest.getAddress(),
                             signUpRequest.getOccupation(),
                             null
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
        emailConfService.sendEmailForSignup(user.getEmail());
        return ResponseEntity.ok(new MessageResponse("L'utilisateur est bien enregistré"));
    }

    private void addRoleToSet(Set<Role> roles, ERole roleEnum) {
        Role role = roleRepository.findByName(roleEnum)
            .orElseThrow(() -> new RoleNotFoundException("Erreur: Rôle " + roleEnum + " non trouvé"));
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
            userDetails.getFirstname(),
            userDetails.getEmail(),
            roles
        );

        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
            .body(userInfoResponse);
    }

    public ResponseEntity<MessageResponse> logout() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("Utilisateur déconnecté"));
    }
    
}
