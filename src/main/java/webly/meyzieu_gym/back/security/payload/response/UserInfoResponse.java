package webly.meyzieu_gym.back.security.payload.response;

import java.util.List;

public class UserInfoResponse {
    
    private final Long id;
    private final String firstname;
    private final String email;
    private final List<String> roles;

    public UserInfoResponse(Long id, String firstname, String email, List<String> roles) {
        this.id = id;
        this.firstname = firstname;
        this.email = email;
        this.roles = roles;
    }    

    public Long getId(){
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getRoles() {
        return roles;
    }
}
