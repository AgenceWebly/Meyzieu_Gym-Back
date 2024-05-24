package webly.meyzieu_gym.back.security.payload.response;

import java.util.Set;

public class UserInfoResponse {
    
    private final String jwt;
    private final String email;
    private final String firstname;
    private final Set<String> roles;
    private final Long expirationTime;

    public UserInfoResponse(String jwt, String email, String firstname, Set<String> roles, Long expirationTime) {
        this.jwt = jwt;
        this.email = email;
        this.firstname = firstname;
        this.roles = roles;
        this.expirationTime = expirationTime;
    }

    public String getJwt() {
        return jwt;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }
    
}
