package webly.meyzieu_gym.back.registrationmanagement.dto;

import java.util.Date;

public class RegistrationDetailsForMemberDto {
    
    private Long id;
    private Long courseId;
    private String courseName;
    private Long seasonId;
    private Date seasonStartDate;
    private Date seasonEndDate;


    public RegistrationDetailsForMemberDto() {
    }

    public RegistrationDetailsForMemberDto(
            Long id, 
            Long courseId, 
            String courseName, 
            Long seasonId, 
            Date seasonStartDate, 
            Date seasonEndDate) {
        this.id = id;
        this.courseId = courseId;
        this.courseName = courseName;
        this.seasonId = seasonId;
        this.seasonStartDate = seasonStartDate;
        this.seasonEndDate = seasonEndDate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return this.courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getSeasonId() {
        return this.seasonId;
    }

    public void setSeasonId(Long seasonId) {
        this.seasonId = seasonId;
    }

    public Date getSeasonStartDate() {
        return this.seasonStartDate;
    }

    public void setSeasonStartDate(Date seasonStartDate) {
        this.seasonStartDate = seasonStartDate;
    }

    public Date getSeasonEndDate() {
        return this.seasonEndDate;
    }

    public void setSeasonEndDate(Date seasonEndDate) {
        this.seasonEndDate = seasonEndDate;
    }

}
