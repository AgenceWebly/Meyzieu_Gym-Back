package webly.meyzieu_gym.back.membermanagement.dto;

public class MemberDto {
    private Long id;
    private String firstname;
    private String lastname;
    private boolean isAllowedToLeave;
    private boolean isFirstAidApproved;
    private boolean isPhotoApproved;
    private boolean isTransportApproved;
    private boolean isJudge;
    private String profilePictureUrl;

    public MemberDto() {}

    public MemberDto(
            Long id, 
            String firstname, 
            String lastname, 
            boolean isAllowedToLeave, 
            boolean isFirstAidApproved, 
            boolean isPhotoApproved, 
            boolean isTransportApproved, 
            boolean isJudge, 
            String profilePictureUrl) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.isAllowedToLeave = isAllowedToLeave;
        this.isFirstAidApproved = isFirstAidApproved;
        this.isPhotoApproved = isPhotoApproved;
        this.isTransportApproved = isTransportApproved;
        this.isJudge = isJudge;
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

    public boolean isIsAllowedToLeave() {
        return this.isAllowedToLeave;
    }

    public boolean getIsAllowedToLeave() {
        return this.isAllowedToLeave;
    }

    public void setIsAllowedToLeave(boolean isAllowedToLeave) {
        this.isAllowedToLeave = isAllowedToLeave;
    }

    public boolean isIsFirstAidApproved() {
        return this.isFirstAidApproved;
    }

    public boolean getIsFirstAidApproved() {
        return this.isFirstAidApproved;
    }

    public void setIsFirstAidApproved(boolean isFirstAidApproved) {
        this.isFirstAidApproved = isFirstAidApproved;
    }

    public boolean isIsPhotoApproved() {
        return this.isPhotoApproved;
    }

    public boolean getIsPhotoApproved() {
        return this.isPhotoApproved;
    }

    public void setIsPhotoApproved(boolean isPhotoApproved) {
        this.isPhotoApproved = isPhotoApproved;
    }

    public boolean isIsTransportApproved() {
        return this.isTransportApproved;
    }

    public boolean getIsTransportApproved() {
        return this.isTransportApproved;
    }

    public void setIsTransportApproved(boolean isTransportApproved) {
        this.isTransportApproved = isTransportApproved;
    }

    public boolean isIsJudge() {
        return this.isJudge;
    }

    public boolean getIsJudge() {
        return this.isJudge;
    }

    public void setIsJudge(boolean isJudge) {
        this.isJudge = isJudge;
    }

    public String getProfilePictureUrl() {
        return this.profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
}
