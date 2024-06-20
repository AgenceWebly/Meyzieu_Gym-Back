package webly.meyzieu_gym.back.usermanagement.user.dto;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;

public class AddRibUrlDto {
    
    @NotBlank(message = "L'URL du RIB ne peut pas être vide")
    @URL(message = "Le format de l'URL est invalide")
    private String ribUrl;

    public AddRibUrlDto() {
    }

    public AddRibUrlDto(String ribUrl) {
        this.ribUrl = ribUrl;
    }

    public String getRibUrl() {
        return ribUrl;
    }

    public void setRibUrl(String ribUrl) {
        this.ribUrl = ribUrl;
    }
}
