package webly.meyzieu_gym.back.coursemanagement.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CreateTrainingSlotDto {

    @NotBlank(message = "Le jour ne peut pas être vide")
    @Pattern(regexp = "lundi|mardi|mercredi|jeudi|vendredi|samedi|dimanche", message = "Le jour choisi n'est pas correct")
    private String day;
    
    @NotNull(message = "L'heure de début ne peut pas être nulle")
    private LocalTime startTime;
    
    @NotNull(message = "L'heure de fin ne peut pas être nulle")
    private LocalTime endTime;

    public CreateTrainingSlotDto() {
    }

    public CreateTrainingSlotDto(String day, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}