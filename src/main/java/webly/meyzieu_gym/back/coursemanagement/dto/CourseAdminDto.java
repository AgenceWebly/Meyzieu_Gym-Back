package webly.meyzieu_gym.back.coursemanagement.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import webly.meyzieu_gym.back.membermanagement.dto.MemberDto;

public class CourseAdminDto {
    
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
    private int remainingSlots;
    private List<MemberDto> members;

    public CourseAdminDto() {}

    public CourseAdminDto(
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
            int remainingSlots,
            List<MemberDto> members) {
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
        this.members = members;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SeasonDto getSeason() {
        return this.season;
    }

    public void setSeason(SeasonDto season) {
        this.season = season;
    }

    public ProgramDto getProgram() {
        return this.program;
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
        return this.registrationStartDate;
    }

    public void setRegistrationStartDate(LocalDateTime registrationStartDate) {
        this.registrationStartDate = registrationStartDate;
    }

    public LocalDateTime getRegistrationEndDate() {
        return this.registrationEndDate;
    }

    public void setRegistrationEndDate(LocalDateTime registrationEndDate) {
        this.registrationEndDate = registrationEndDate;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getMaxMembers() {
        return this.maxMembers;
    }

    public void setMaxMembers(Integer maxMembers) {
        this.maxMembers = maxMembers;
    }

    public Integer getMinAge() {
        return this.minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return this.maxAge;
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

    public int getRemainingSlots() {
        return this.remainingSlots;
    }

    public void setRemainingSlots(int remainingSlots) {
        this.remainingSlots = remainingSlots;
    }

    public List<MemberDto> getMembers() {
        return this.members;
    }

    public void setMembers(List<MemberDto> members) {
        this.members = members;
    }    
}
