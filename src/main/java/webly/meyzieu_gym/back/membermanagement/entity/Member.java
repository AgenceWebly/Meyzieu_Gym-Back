package webly.meyzieu_gym.back.membermanagement.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", nullable = false, length = 100)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 100)
    private String lastname;

    @Column(name = "birthdate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Column(name = "gender", nullable = false, length = 15)
    private String gender;

    @Column(name = "school", nullable = false, length = 255)
    private String school;

    @Column(name = "photo_approved", nullable = false)
    private boolean isPhotoApproved;

    @Column(name = "transport_approved", nullable = false)
    private boolean isTransportApproved;

    @Column(name = "first_aid_approved", nullable = false)
    private boolean isFirstAidApproved;

    @Column(name = "allowed_to_leave", nullable = false)
    private boolean isAllowedToLeave;

    @Column(name = "profile_picture_url", nullable = false)
    private String profilePictureUrl;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmergencyContact> emergencyContacts;
    
    public Member() {
    }

    public Member(String firstname, String lastname, Date birthdate, String gender, String school, boolean isPhotoApproved, boolean isTransportApproved, boolean isFirstAidApproved, boolean isAllowedToLeave, String profilePictureUrl) {
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public boolean isPhotoApproved() {
        return isPhotoApproved;
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
        return isFirstAidApproved;
    }

    public void setFirstAidApproved(boolean isFirstAidApproved) {
        this.isFirstAidApproved = isFirstAidApproved;
    }

    public boolean isAllowedToLeave() {
        return isAllowedToLeave;
    }

    public void setAllowedToLeave(boolean isAllowedToLeave) {
        this.isAllowedToLeave = isAllowedToLeave;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public List<EmergencyContact> getEmergencyContacts() {
        return emergencyContacts;
    }

    public void setEmergencyContacts(List<EmergencyContact> emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }
}
