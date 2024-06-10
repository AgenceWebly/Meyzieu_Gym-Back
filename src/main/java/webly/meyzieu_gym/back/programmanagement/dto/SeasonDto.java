package webly.meyzieu_gym.back.programmanagement.dto;

import java.util.Date;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public class SeasonDto {
    
    private Long id;

    @NotNull(message = "Start date cannot be null")
    private Date startDate;

    @NotNull(message = "End date cannot be null")
    @Future(message = "End date must be in the future")
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
