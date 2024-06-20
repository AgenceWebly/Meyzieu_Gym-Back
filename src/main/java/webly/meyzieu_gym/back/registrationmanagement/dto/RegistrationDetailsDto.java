package webly.meyzieu_gym.back.registrationmanagement.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import webly.meyzieu_gym.back.coursemanagement.dto.TrainingSlotDto;

public class RegistrationDetailsDto {
    
    private String memberFirstname;
    private String memberLastname;
    private Date memberBirthdate;
    private String courseName;
    private List<TrainingSlotDto> trainingSlots;
    private BigDecimal registrationFee;


    public RegistrationDetailsDto() {
    }

    public RegistrationDetailsDto(
            String memberFirstname, 
            String memberLastname, 
            Date memberBirthdate, 
            String courseName, 
            List<TrainingSlotDto> trainingSlots, 
            BigDecimal registrationFee) {
        this.memberFirstname = memberFirstname;
        this.memberLastname = memberLastname;
        this.memberBirthdate = memberBirthdate;
        this.courseName = courseName;
        this.trainingSlots = trainingSlots;
        this.registrationFee = registrationFee;
    }

    public String getMemberFirstname() {
        return this.memberFirstname;
    }

    public void setMemberFirstname(String memberFirstname) {
        this.memberFirstname = memberFirstname;
    }

    public String getMemberLastname() {
        return this.memberLastname;
    }

    public void setMemberLastname(String memberLastname) {
        this.memberLastname = memberLastname;
    }

    public Date getMemberBirthdate() {
        return this.memberBirthdate;
    }

    public void setMemberBirthdate(Date memberBirthdate) {
        this.memberBirthdate = memberBirthdate;
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

}
