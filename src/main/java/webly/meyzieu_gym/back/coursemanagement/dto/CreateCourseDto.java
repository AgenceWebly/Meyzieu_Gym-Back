package webly.meyzieu_gym.back.coursemanagement.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CreateCourseDto {

    @NotNull
    private Long seasonId;

    @NotNull
    private Long programId;

    @NotBlank
    @Size(min = 2, max = 20, message = "The description size isn't right")
    private String courseName;

    @NotNull
    private LocalDateTime registrationStartDate;

    @NotNull
    @Future(message = "Registration end date must be in the future")
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

    @Valid
    @Size(min = 1, message = "At least one training slot is required")
    private List<CreateTrainingSlotDto> createTrainingSlotDtos;

    public CreateCourseDto() {
    }

    public CreateCourseDto(
        Long seasonId, 
        Long programId,
        String courseName,
        LocalDateTime registrationStartDate, 
        LocalDateTime registrationEndDate, 
        BigDecimal price, 
        Integer maxMembers, 
        Integer minAge, 
        Integer maxAge) {
            this.seasonId = seasonId;
            this.programId = programId;
            this.courseName = courseName;
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

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public List<CreateTrainingSlotDto> getCreateTrainingSlotDtos() {
        return this.createTrainingSlotDtos;
    }

    public void setCreateTrainingSlotDtos(List<CreateTrainingSlotDto> createTrainingSlotDtos) {
        this.createTrainingSlotDtos = createTrainingSlotDtos;
    }
}
