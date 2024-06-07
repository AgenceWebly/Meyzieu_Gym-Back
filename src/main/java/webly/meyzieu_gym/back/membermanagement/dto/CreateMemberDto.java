package webly.meyzieu_gym.back.membermanagement.dto;

import java.util.Date;

public class CreateMemberDto {
    
    private String firstname;
    private String lastname;
    private Date birthdate;
    private String gender;
    private String school;
    private boolean isPhotoApproved;
    private boolean isTransportApproved;
    private boolean isFirstAidApproved;
    private boolean isAllowedToLeave;
    private String profilePictureUrl;
    private String relationToMember;

    public CreateMemberDto() {
    }

    public CreateMemberDto(String firstname, String lastname, Date birthdate, String gender, String school, boolean isPhotoApproved, boolean isTransportApproved, boolean isFirstAidApproved, boolean isAllowedToLeave, String profilePictureUrl, String relationToMember) {
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

}
