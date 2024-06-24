package webly.meyzieu_gym.back.registrationmanagement.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class NewRegistrationDto {
    
    @NotNull(message = "L'identifiant du membre ne peut pas être nul")
    private Long memberId;

    @NotNull(message = "L'identifiant du cours ne peut pas être nul")
    private Long courseId;

    @NotNull(message = "Les frais d'inscription ne peuvent pas être nuls")
    @DecimalMin(value = "0.00", message = "Les frais d'inscription doivent être au moins de 0,00")
    @Digits(integer = 3, fraction = 2, message = "Les frais d'inscription doivent avoir au plus 3 chiffres avant la virgule et 2 chiffres après la virgule")
    private BigDecimal registrationFee;

    @NotBlank(message = "Le statut de l'inscription ne peut pas être vide")
    @Pattern(regexp = "cours choisi", message = "Le statut n'est pas correct")
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