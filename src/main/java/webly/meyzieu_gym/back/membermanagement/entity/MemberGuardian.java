package webly.meyzieu_gym.back.membermanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import webly.meyzieu_gym.back.usermanagement.user.entity.User;

@Entity
@Table(name = "member_guardian")
public class MemberGuardian {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "relation_to_member", nullable = false, length = 100)
    private String relationToMember;

    @Column(name = "is_referent", nullable = false)
    private boolean isReferent;

    public MemberGuardian() {
    }

    public MemberGuardian(Member member, User user, String relationToMember, boolean isReferent) {
        this.member = member;
        this.user = user;
        this.relationToMember = relationToMember;
        this.isReferent = isReferent;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return this.member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRelationToMember() {
        return this.relationToMember;
    }

    public void setRelationToMember(String relationToMember) {
        this.relationToMember = relationToMember;
    }

    public boolean isReferent() {
        return this.isReferent;
    }

    public void setReferent(boolean isReferent) {
        this.isReferent = isReferent;
    }

}
