package webly.meyzieu_gym.back.membermanagement.dto;

public class MemberListAdminDto {
    
    private Long id;
    private String firstname;
    private String lastname;
    private String profilePictureUrl;

    public MemberListAdminDto() {
    }

    public MemberListAdminDto(Long id, String firstname, String lastname, String profilePictureUrl) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.profilePictureUrl = profilePictureUrl;
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

    public String getProfilePictureUrl() {
        return this.profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

}
