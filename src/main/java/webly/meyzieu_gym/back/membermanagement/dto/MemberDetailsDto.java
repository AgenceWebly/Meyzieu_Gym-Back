package webly.meyzieu_gym.back.membermanagement.dto;

import webly.meyzieu_gym.back.registrationmanagement.dto.RegistrationDetailsForCertificateDto;
import webly.meyzieu_gym.back.registrationmanagement.dto.RegistrationDetailsForMemberDto;
import webly.meyzieu_gym.back.usermanagement.user.dto.GuardianDto;

import java.util.Date;
import java.util.List;

public class MemberDetailsDto {

    private Long id;
    private String firstname;
    private String lastname;
    private Date birthdate;
    private String school;
    private boolean isAllowedToLeave;
    private boolean isFirstAidApproved;
    private boolean isPhotoApproved;
    private boolean isTransportApproved;
    private String profilePictureUrl;
    private String sportPassUrl;
    private String regionPassUrl;
    private List<GuardianDto> guardians;
    private List<RegistrationDetailsForCertificateDto> registrations;

    private List<EmergencyContactDto> emergencyContacts;

    public MemberDetailsDto() {
    }

    public MemberDetailsDto(
            Long id,
            String firstname,
            String lastname,
            Date birthdate,
            String school,
            boolean isAllowedToLeave,
            boolean isFirstAidApproved,
            boolean isPhotoApproved,
            boolean isTransportApproved,
            String profilePictureUrl,
            String sportPassUrl,
            String regionPassUrl,
            List<GuardianDto> guardians,
            List<RegistrationDetailsForCertificateDto> registrations,
            List<EmergencyContactDto> emergencyContacts ) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
        this.school = school;
        this.isAllowedToLeave = isAllowedToLeave;
        this.isFirstAidApproved = isFirstAidApproved;
        this.isPhotoApproved = isPhotoApproved;
        this.isTransportApproved = isTransportApproved;
        this.profilePictureUrl = profilePictureUrl;
        this.sportPassUrl = sportPassUrl;
        this.regionPassUrl = regionPassUrl;
        this.guardians = guardians;
        this.registrations = registrations;
        this.emergencyContacts = emergencyContacts;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public boolean isAllowedToLeave() {
        return this.isAllowedToLeave;
    }

    public void setAllowedToLeave(boolean isAllowedToLeave) {
        this.isAllowedToLeave = isAllowedToLeave;
    }

    public boolean isFirstAidApproved() {
        return this.isFirstAidApproved;
    }

    public void setFirstAidApproved(boolean isFirstAidApproved) {
        this.isFirstAidApproved = isFirstAidApproved;
    }

    public boolean isPhotoApproved() {
        return this.isPhotoApproved;
    }

    public void setPhotoApproved(boolean isPhotoApproved) {
        this.isPhotoApproved = isPhotoApproved;
    }

    public boolean isTransportApproved() {
        return this.isTransportApproved;
    }

    public void setTransportApproved(boolean isTransportApproved) {
        this.isTransportApproved = isTransportApproved;
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

    public List<GuardianDto> getGuardians() {
        return this.guardians;
    }

    public void setGuardians(List<GuardianDto> guardians) {
        this.guardians = guardians;
    }

    public List<RegistrationDetailsForCertificateDto> getRegistrations() {
        return this.registrations;
    }

    public void setRegistrations(List<RegistrationDetailsForCertificateDto> registrations) {
        this.registrations = registrations;
    }

    public List<EmergencyContactDto> getEmergencyContacts() {
        return emergencyContacts;
    }

    public void setEmergencyContacts(List<EmergencyContactDto> emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }
}
