package webly.meyzieu_gym.back.programmanagement.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateProgramSeasonDto {

    @NotNull
    private Long seasonId;

    @NotNull
    private Long programId;

    @NotNull
    private LocalDateTime registrationStartDate;

    @NotNull
    private LocalDateTime registrationEndDate;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @NotNull
    @Positive
    private Integer maxMembers;

    @NotNull
    @Positive
    private Integer minAge;

    @NotNull
    @Positive
    private Integer maxAge;

    public CreateProgramSeasonDto() {
    }

    public CreateProgramSeasonDto(
        Long seasonId, 
        Long programId, 
        LocalDateTime registrationStartDate, 
        LocalDateTime registrationEndDate, 
        BigDecimal price, 
        Integer maxMembers, 
        Integer minAge, 
        Integer maxAge) {
            this.seasonId = seasonId;
            this.programId = programId;
            this.registrationStartDate = registrationStartDate;
            this.registrationEndDate = registrationEndDate;
            this.price = price;
            this.maxMembers = maxMembers;
            this.minAge = minAge;
            this.maxAge = maxAge;
    }

    public Long getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Long seasonId) {
        this.seasonId = seasonId;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public LocalDateTime getRegistrationStartDate() {
        return registrationStartDate;
    }

    public void setRegistrationStartDate(LocalDateTime registrationStartDate) {
        this.registrationStartDate = registrationStartDate;
    }

    public LocalDateTime getRegistrationEndDate() {
        return registrationEndDate;
    }

    public void setRegistrationEndDate(LocalDateTime registrationEndDate) {
        this.registrationEndDate = registrationEndDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(Integer maxMembers) {
        this.maxMembers = maxMembers;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }
}
