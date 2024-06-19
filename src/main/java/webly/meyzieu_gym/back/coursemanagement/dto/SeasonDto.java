package webly.meyzieu_gym.back.coursemanagement.dto;

import java.util.Date;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public class SeasonDto {
    
    private Long id;

    @NotNull(message = "La date de début ne peut pas être nulle")
    private Date startDate;
    
    @NotNull(message = "La date de fin ne peut pas être nulle")
    @Future(message = "La date de fin doit être dans le futur")
    private Date endDate;

    public SeasonDto() {
    }

    public SeasonDto(Long id, Date startDate, Date endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
