package webly.meyzieu_gym.back.membermanagement.dto;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateMemberDto {

    @NotBlank(message = "L'école ne peut pas être vide")
    @Size(min = 10, max = 255, message = "La taille de l'école doit être comprise entre 10 et 255 caractères")
    private String school;

    @URL(message = "Le format de l'URL est invalide")
    private String profilePictureUrl;

    @URL(message = "Le format de l'URL est invalide")
    private String sportPassUrl;
    
    @URL(message = "Le format de l'URL est invalide")
    private String regionPassUrl;

    public UpdateMemberDto() {
    }

    public UpdateMemberDto(String school, String profilePictureUrl, String sportPassUrl, String regionPassUrl) {
        this.school = school;
        this.profilePictureUrl = profilePictureUrl;
        this.sportPassUrl = sportPassUrl;
        this.regionPassUrl = regionPassUrl;
    }

    public String getSchool() {
        return this.school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getProfilePictureUrl() {
        return this.profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getSportPassUrl() {
        return this.sportPassUrl;
    }

    public void setSportPassUrl(String sportPassUrl) {
        this.sportPassUrl = sportPassUrl;
    }

    public String getRegionPassUrl() {
        return this.regionPassUrl;
    }

    public void setRegionPassUrl(String regionPassUrl) {
        this.regionPassUrl = regionPassUrl;
    }
    
}
