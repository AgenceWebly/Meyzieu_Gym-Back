package webly.meyzieu_gym.back.membermanagement.dto;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateMemberDto {
    
    @NotBlank
    @Size(min = 3, max = 100)
    private String firstname;

    @NotBlank
    @Size(min = 3, max = 100)
    private String lastname;
    
    @NotNull
    @Past(message = "Birthdate must be in the past")
    private Date birthdate;
    
    @NotBlank
    @Pattern(regexp = "male|female", message = "Gender must be male or female")
    private String gender;
    
    @NotBlank
    @Size(min = 10, max = 255)
    private String school;
    
    @NotNull
    private boolean isPhotoApproved;
    
    @NotNull
    private boolean isTransportApproved;
    
    @NotNull
    private boolean isFirstAidApproved;
    
    @NotNull
    private boolean isAllowedToLeave;
    
    @URL(message = "Invalid URL format")
    private String profilePictureUrl;
    
    @NotBlank
    @Pattern(regexp = "parent|grand-parent|relative|other", message = "The sent relation to member isn't right")
    private String relationToMember;

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

    public void setIsPhotoApproved(boolean isPhotoApproved) {
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

    public void setIsFirstAidApproved(boolean isFirstAidApproved) {
        this.isFirstAidApproved = isFirstAidApproved;
    }

    public boolean isAllowedToLeave() {
        return this.isAllowedToLeave;
    }

    public void setIsAllowedToLeave(boolean isAllowedToLeave) {
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
