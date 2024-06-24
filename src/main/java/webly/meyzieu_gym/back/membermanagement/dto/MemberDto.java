package webly.meyzieu_gym.back.membermanagement.dto;

public class MemberDto {
    private Long id;
    private String firstname;
    private String lastname;
    private boolean isAllowedToLeave;
    private boolean isFirstAidApproved;
    private boolean isPhotoApproved;
    private boolean isTransportApproved;
    private String profilePictureUrl;
    private String sportPassUrl;
    private String regionPassUrl;

    public MemberDto() {}

    public MemberDto(
            Long id, 
            String firstname, 
            String lastname, 
            boolean isAllowedToLeave, 
            boolean isFirstAidApproved, 
            boolean isPhotoApproved, 
            boolean isTransportApproved, 
            String profilePictureUrl,
            String sportPassUrl,
            String regionPassUrl) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.isAllowedToLeave = isAllowedToLeave;
        this.isFirstAidApproved = isFirstAidApproved;
        this.isPhotoApproved = isPhotoApproved;
        this.isTransportApproved = isTransportApproved;
        this.profilePictureUrl = profilePictureUrl;
        this.sportPassUrl = sportPassUrl;
        this.regionPassUrl = regionPassUrl;
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
}
