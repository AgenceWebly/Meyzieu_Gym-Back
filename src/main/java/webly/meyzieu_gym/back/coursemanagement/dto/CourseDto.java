package webly.meyzieu_gym.back.coursemanagement.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CourseDto {

    private Long id;
    private Long seasonId;
    private Long programId;
    private LocalDateTime registrationStartDate;
    private LocalDateTime registrationEndDate;
    private BigDecimal price;
    private Integer maxMembers;
    private Integer minAge;
    private Integer maxAge;
    private List<TrainingSlotDto> trainingSlotDtos;

    public CourseDto() {
    }

    public CourseDto(
            Long id, 
            Long seasonId,
            Long programId,
            LocalDateTime registrationStartDate,
            LocalDateTime registrationEndDate,
            BigDecimal price,
            Integer maxMembers,
            Integer minAge,
            Integer maxAge,
            List<TrainingSlotDto> trainingSlotDtos) {
        this.id = id;
        this.seasonId = seasonId;
        this.programId = programId;
        this.registrationStartDate = registrationStartDate;
        this.registrationEndDate = registrationEndDate;
        this.price = price;
        this.maxMembers = maxMembers;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.trainingSlotDtos = trainingSlotDtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<TrainingSlotDto> getTrainingSlotDtos() {
        return trainingSlotDtos;
    }

    public void setTrainingSlotDtos(List<TrainingSlotDto> trainingSlotDtos) {
        this.trainingSlotDtos = trainingSlotDtos;
    }
}
