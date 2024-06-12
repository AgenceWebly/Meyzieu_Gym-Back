package webly.meyzieu_gym.back.registrationmanagement.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class NewRegistrationDto {
    
    @NotNull
    private Long memberId;

    @NotNull
    private Long courseId;

    @NotNull
    @DecimalMin("0.00")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal registrationFee;

    @NotBlank
    @Size(max = 30)
    private String paymentMethod;

    @NotBlank
    @Size(max = 30)
    private String paymentStatus;

    @NotBlank
    @Size(max = 30)
    private String registrationStatus;

    @NotNull
    private LocalDateTime registrationDate;


    public NewRegistrationDto() {
    }

    public NewRegistrationDto(
        Long memberId,
        Long courseId,
        BigDecimal registrationFee,
        String paymentMethod,
        String paymentStatus,
        String registrationStatus,
        LocalDateTime registrationDate) {
            this.memberId = memberId;
            this.courseId = courseId;
            this.registrationFee = registrationFee;
            this.paymentMethod = paymentMethod;
            this.paymentStatus = paymentStatus;
            this.registrationStatus = registrationStatus;
            this.registrationDate = registrationDate;
    }

    public Long getMemberId() {
        return this.memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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

}
