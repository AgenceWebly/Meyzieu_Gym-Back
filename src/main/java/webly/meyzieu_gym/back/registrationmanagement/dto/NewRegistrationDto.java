package webly.meyzieu_gym.back.registrationmanagement.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

public class NewRegistrationDto {
    
    @NotNull
    private Long memberId;

    @NotNull
    private Long courseId;

    @NotNull
    @DecimalMin("0.00")
    @Digits(integer = 10, fraction = 2)
    private BigDecimal registrationFee;

    public NewRegistrationDto() {
    }

    public NewRegistrationDto(
        Long memberId,
        Long courseId,
        BigDecimal registrationFee) {
            this.memberId = memberId;
            this.courseId = courseId;
            this.registrationFee = registrationFee;
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
}