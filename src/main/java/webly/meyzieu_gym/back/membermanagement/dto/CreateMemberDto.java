package webly.meyzieu_gym.back.membermanagement.dto;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateMemberDto {
    
    @NotBlank(message = "Le prénom ne peut pas être vide")
    @Size(min = 3, max = 100, message = "La taille du prénom n'est pas valide")
    private String firstname;

    @NotBlank(message = "Le nom de famille ne peut pas être vide")
    @Size(min = 3, max = 100, message = "La taille du nom de famille n'est pas valide")
    private String lastname;

    @NotNull(message = "La date de naissance ne peut pas être nulle")
    @Past(message = "La date de naissance doit être dans le passé")
    private Date birthdate;

    @NotBlank(message = "Le genre ne peut pas être vide")
    @Pattern(regexp = "male|female", message = "Le genre doit être male ou female")
    private String gender;

    @NotBlank(message = "L'école ne peut pas être vide")
    @Size(min = 10, max = 255, message = "La taille de l'école doit être comprise entre 10 et 255 caractères")
    private String school;

    @NotNull(message = "Veuillez indiquer si la photo est approuvée")
    private boolean isPhotoApproved;

    @NotNull(message = "Veuillez indiquer si le transport est approuvé")
    private boolean isTransportApproved;

    @NotNull(message = "Veuillez indiquer si les premiers secours sont approuvés")
    private boolean isFirstAidApproved;

    @NotNull(message = "Veuillez indiquer si l'élève est autorisé à partir")
    private boolean isAllowedToLeave;

    @URL(message = "Le format de l'URL est invalide")
    private String profilePictureUrl;

    @NotBlank(message = "La relation avec le membre ne peut pas être vide")
    @Pattern(regexp = "father|mother|other", message = "La relation envoyée au membre n'est pas correcte")
    private String relationToMember;

    @Valid
    @Size(min = 1, message = "Au moins un contact d'urgence est requis")
    private List<EmergencyContactDto> emergencyContacts;
    
    public CreateMemberDto() {
    }

    public CreateMemberDto(String firstname, String lastname, Date birthdate, String gender, String school, boolean isPhotoApproved, boolean isTransportApproved, boolean isFirstAidApproved, boolean isAllowedToLeave, String profilePictureUrl, String relationToMember,  List<EmergencyContactDto> emergencyContacts) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.gender = gender;
        this.school = school;
        this.isPhotoApproved = isPhotoApproved;
        this.isTransportApproved = isTransportApproved;
        this.isFirstAidApproved = isFirstAidApproved;
        this.isAllowedToLeave = isAllowedToLeave;
        this.profilePictureUrl = profilePictureUrl;
        this.relationToMember = relationToMember;
        this.emergencyContacts = emergencyContacts;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSchool() {
        return this.school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public boolean isPhotoApproved() {
        return this.isPhotoApproved;
    }

    public void setPhotoApproved(boolean isPhotoApproved) {
        this.isPhotoApproved = isPhotoApproved;
    }

    public boolean isTransportApproved() {
        return isTransportApproved;
    }

    public void setTransportApproved(boolean isTransportApproved) {
        this.isTransportApproved = isTransportApproved;
    }

    public boolean isFirstAidApproved() {
        return this.isFirstAidApproved;
    }

    public void setFirstAidApproved(boolean isFirstAidApproved) {
        this.isFirstAidApproved = isFirstAidApproved;
    }

    public boolean isAllowedToLeave() {
        return this.isAllowedToLeave;
    }

    public void setAllowedToLeave(boolean isAllowedToLeave) {
        this.isAllowedToLeave = isAllowedToLeave;
    }

    public String getProfilePictureUrl() {
        return this.profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getRelationToMember() {
        return this.relationToMember;
    }

    public void setRelationToMember(String relationToMember) {
        this.relationToMember = relationToMember;
    }

    public List<EmergencyContactDto> getEmergencyContacts() {
        return this.emergencyContacts;
    }

    public void setEmergencyContacts(List<EmergencyContactDto> emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }
}
