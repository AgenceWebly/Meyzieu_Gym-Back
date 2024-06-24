package webly.meyzieu_gym.back.registrationmanagement.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import webly.meyzieu_gym.back.coursemanagement.entity.Course;
import webly.meyzieu_gym.back.membermanagement.entity.Member;

@Entity
@Table(name = "registration")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Column(name = "registration_fee", nullable = false, precision = 10, scale = 2)
    private BigDecimal registrationFee;

    @Column(name = "payment_method", nullable = false, length = 30)
    private String paymentMethod;

    @Column(name = "payment_status", nullable = false, length = 30)
    private String paymentStatus;

    @Column(name = "registration_status", nullable = false, length = 30)
    private String registrationStatus;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;

    @Column(name = "health_certificate_file_url", length = 2048)
    private String healthCertificateFileUrl;

    @Column(name = "is_health_certificate_required", nullable = false)
    private boolean isHealthCertificateRequired;

    public Registration() {
    }

    public Registration(
        Member member, 
        Course course, 
        BigDecimal registrationFee, 
        String paymentMethod, 
        String paymentStatus, 
        String registrationStatus, 
        LocalDateTime registrationDate, 
        String healthCertificateFileUrl, 
        boolean isHealthCertificateRequired) {
            this.member = member;
            this.course = course;
            this.registrationFee = registrationFee;
            this.paymentMethod = paymentMethod;
            this.paymentStatus = paymentStatus;
            this.registrationStatus = registrationStatus;
            this.registrationDate = registrationDate;
            this.healthCertificateFileUrl = healthCertificateFileUrl;
            this.isHealthCertificateRequired = isHealthCertificateRequired;
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

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public BigDecimal getRegistrationFee() {
        return this.registrationFee;
    }

    public void setRegistrationFee(BigDecimal registrationFee) {
        this.registrationFee = registrationFee;
    }

    public String getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return this.paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getRegistrationStatus() {
        return this.registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public LocalDateTime getRegistrationDate() {
        return this.registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getHealthCertificateFileUrl() {
        return this.healthCertificateFileUrl;
    }

    public void setHealthCertificateFileUrl(String healthCertificateFileUrl) {
        this.healthCertificateFileUrl = healthCertificateFileUrl;
    }

    public boolean isHealthCertificateRequired() {
        return this.isHealthCertificateRequired;
    }

    public void setHealthCertificateRequired(boolean isHealthCertificateRequired) {
        this.isHealthCertificateRequired = isHealthCertificateRequired;
    }
    
}
