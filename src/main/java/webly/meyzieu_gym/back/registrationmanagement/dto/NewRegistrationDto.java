package webly.meyzieu_gym.back.registrationmanagement.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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
    @Pattern(regexp = "cours choisi", message = "The status isn't the right one")
    private String registrationStatus;

    public NewRegistrationDto() {
    }

    public NewRegistrationDto(
        Long memberId,
        Long courseId,
        BigDecimal registrationFee,
        String registrationStatus) {
            this.memberId = memberId;
            this.courseId = courseId;
            this.registrationFee = registrationFee;
            this.registrationStatus = registrationStatus;
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

    public String getRegistrationStatus() {
        return this.registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
}