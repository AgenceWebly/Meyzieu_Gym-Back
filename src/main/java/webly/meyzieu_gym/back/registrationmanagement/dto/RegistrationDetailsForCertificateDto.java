package webly.meyzieu_gym.back.registrationmanagement.dto;

import webly.meyzieu_gym.back.coursemanagement.dto.TrainingSlotDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class RegistrationDetailsForCertificateDto {

    private String courseName;
    private List<TrainingSlotDto> trainingSlots;
    private BigDecimal registrationFee;
    private Date seasonStartDate;
    private Date seasonEndDate;


    public RegistrationDetailsForCertificateDto() {
    }

    public RegistrationDetailsForCertificateDto(
            String courseName,
            List<TrainingSlotDto> trainingSlots,
            BigDecimal registrationFee,
            Date seasonStartDate,
            Date seasonEndDate) {
        this.courseName = courseName;
        this.trainingSlots = trainingSlots;
        this.registrationFee = registrationFee;
        this.seasonStartDate = seasonStartDate;
        this.seasonEndDate = seasonEndDate;
    }


    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<TrainingSlotDto> getTrainingSlots() {
        return this.trainingSlots;
    }

    public void setTrainingSlots(List<TrainingSlotDto> trainingSlots) {
        this.trainingSlots = trainingSlots;
    }

    public BigDecimal getRegistrationFee() {
        return this.registrationFee;
    }

    public void setRegistrationFee(BigDecimal registrationFee) {
        this.registrationFee = registrationFee;
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

