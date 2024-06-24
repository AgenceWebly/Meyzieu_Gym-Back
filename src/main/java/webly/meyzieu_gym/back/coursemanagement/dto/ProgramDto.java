package webly.meyzieu_gym.back.coursemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProgramDto {
    
    private Long id;

    @NotBlank(message = "Le nom ne peut pas être vide")
    @Size(min = 2, max = 100, message = "La taille du nom doit être comprise entre 2 et 100 caractères")
    private String name;
    
    @NotBlank(message = "La description ne peut pas être vide")
    @Size(min = 3, max = 255, message = "La taille de la description doit être comprise entre 3 et 255 caractères")
    private String description;

    @NotNull(message = "Veuillez indiquer si la compétition est incluse")
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
