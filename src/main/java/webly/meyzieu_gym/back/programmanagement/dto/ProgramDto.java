package webly.meyzieu_gym.back.programmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProgramDto {
    
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100, message = "The name size isn't right")
    private String name;

    @NotBlank
    @Size(min = 3, max = 255, message = "The description size isn't right")
    private String description;

    @NotNull
    private Boolean isIncludingCompetition;

    public ProgramDto() {
    }

    public ProgramDto(Long id, String name, String description, Boolean isIncludingCompetition) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isIncludingCompetition = isIncludingCompetition;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isIncludingCompetition() {
        return this.isIncludingCompetition;
    }

    public void setIncludingCompetition(Boolean isIncludingCompetition) {
        this.isIncludingCompetition = isIncludingCompetition;
    }
    
}
