package webly.meyzieu_gym.back.usermanagement.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "guardian_info")
public class GuardianInfo {

    @Id
    private Long userId;

    @Column(name = "rib_url", length = 2048)
    private String ribUrl;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    public GuardianInfo() {
    }

    public GuardianInfo(Long userId, String ribUrl, User user) {
        this.userId = userId;
        this.ribUrl = ribUrl;
        this.user = user;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRibUrl() {
        return this.ribUrl;
    }

    public void setRibUrl(String ribUrl) {
        this.ribUrl = ribUrl;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    
}
