package webly.meyzieu_gym.back.security.payload.request;

import jakarta.validation.constraints.NotBlank;

public class LogingRequest {
    
    @NotBlank
    private String email;
    
    @NotBlank
    private String password;

    public LogingRequest() {

    }

    public LogingRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
