package webly.meyzieu_gym.back.membermanagement.dto;

public class MemberListDto {
    
    private Long id;
    private String firstname;
    private String lastname;
    private String profilePictureUrl;
    private String registrationStatus;

    public MemberListDto() {
    }

    public MemberListDto(Long id, String firstname, String lastname, String profilePictureUrl, String registrationStatus) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.profilePictureUrl = profilePictureUrl;
        this.registrationStatus = registrationStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
}
