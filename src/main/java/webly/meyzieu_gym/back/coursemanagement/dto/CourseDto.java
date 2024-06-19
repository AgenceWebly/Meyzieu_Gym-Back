package webly.meyzieu_gym.back.coursemanagement.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CourseDto {

    private Long id;
    private SeasonDto season;
    private ProgramDto program;
    private String courseName;
    private LocalDateTime registrationStartDate;
    private LocalDateTime registrationEndDate;
    private BigDecimal price;
    private Integer maxMembers;
    private Integer minAge;
    private Integer maxAge;
    private List<TrainingSlotDto> trainingSlots;
    private Integer remainingSlots;
    private Integer userRegistrationsCount;

    public CourseDto() {
    }

    public CourseDto(
            Long id, 
            SeasonDto season,
            ProgramDto program,
            String courseName,
            LocalDateTime registrationStartDate,
            LocalDateTime registrationEndDate,
            BigDecimal price,
            Integer maxMembers,
            Integer minAge,
            Integer maxAge,
            List<TrainingSlotDto> trainingSlots,
            Integer remainingSlots,
            Integer userRegistrationsCount) {
        this.id = id;
        this.season = season;
        this.program = program;
        this.courseName = courseName;
        this.registrationStartDate = registrationStartDate;
        this.registrationEndDate = registrationEndDate;
        this.price = price;
        this.maxMembers = maxMembers;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.trainingSlots = trainingSlots;
        this.remainingSlots = remainingSlots;
        this.userRegistrationsCount = userRegistrationsCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SeasonDto getSeason() {
        return season;
    }

    public void setSeason(SeasonDto season) {
        this.season = season;
    }

    public ProgramDto getProgram() {
        return program;
    }

    public void setProgram(ProgramDto program) {
        this.program = program;
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

    public List<TrainingSlotDto> getTrainingSlots() {
        return this.trainingSlots;
    }
    
    public void setTrainingSlots(List<TrainingSlotDto> trainingSlots) {
        this.trainingSlots = trainingSlots;
    }

    public Integer getRemainingSlots() {
        return this.remainingSlots;
    }

    public void setRemainingSlots(Integer remainingSlots) {
        this.remainingSlots = remainingSlots;
    }

    public Integer getUserRegistrationsCount() {
        return userRegistrationsCount;
    }

    public void setUserRegistrationsCount(Integer userRegistrationsCount) {
        this.userRegistrationsCount = userRegistrationsCount;
    }
}
