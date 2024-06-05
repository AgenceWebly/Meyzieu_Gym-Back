package webly.meyzieu_gym.back.security.payload.response;

import java.util.List;

public class UserInfoResponse {
    
    private final Long id;
    private final String email;
    private final List<String> roles;

    public UserInfoResponse(Long id, String email, List<String> roles) {
        this.id = id;
        this.email = email;
        this.roles = roles;
    }    

    public String getEmail() {
        return email;
    }

    public Long getId(){
        return id;
    }

    public List<String> getRoles() {
        return roles;
    }
}
