package webly.meyzieu_gym.back.coursemanagement.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CreateCourseDto {

    @NotNull(message = "Veuillez indiquer la saison")
    private Long seasonId;

    @NotNull(message = "Veuillez indiquer le programme")
    private Long programId;

    @NotBlank(message = "Veuillez indiquer le nom du cours")
    @Size(min = 2, max = 20, message = "La taille du nom n'est pas compatible")
    private String courseName;

    @NotNull(message = "Veuillez indiquer la date de début d'inscription")
    private LocalDateTime registrationStartDate;

    @NotNull(message = "Veuillez indiquer la date de fin d'inscription")
    @Future(message = "La date de fin d'inscription doit etre dans le futur")
    private LocalDateTime registrationEndDate;

    @NotNull(message = "Veuillez indiquer le tarif du cours")
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @NotNull(message = "Veuillez indiquer le nombre maximum d'adhérent au cours")
    @Positive(message = "La valeur doit etre positive")
    private Integer maxMembers;

    @NotNull(message = "Veuillez indiquer l'age minimum pour participer au cours")
    @Positive(message = "La valeur doit etre positive")
    private Integer minAge;

    @NotNull(message = "Veuillez indiquer l'age maximum pour participer au cours")
    @Positive(message = "La valeur doit etre positive")
    private Integer maxAge;

    @Valid
    @Size(min = 1, message = "Au moins un créneau d'entraînement est requis")
    private List<CreateTrainingSlotDto> createTrainingSlotDtos;

    public CreateCourseDto() {
    }

    public CreateCourseDto(
        Long seasonId, 
        Long programId,
        String courseName,
        LocalDateTime registrationStartDate, 
        LocalDateTime registrationEndDate, 
        BigDecimal price, 
        Integer maxMembers, 
        Integer minAge, 
        Integer maxAge) {
            this.seasonId = seasonId;
            this.programId = programId;
            this.courseName = courseName;
            this.registrationStartDate = registrationStartDate;
            this.registrationEndDate = registrationEndDate;
            this.price = price;
            this.maxMembers = maxMembers;
            this.minAge = minAge;
            this.maxAge = maxAge;
    }

    public Long getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Long seasonId) {
        this.seasonId = seasonId;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
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

    public List<CreateTrainingSlotDto> getCreateTrainingSlotDtos() {
        return this.createTrainingSlotDtos;
    }

    public void setCreateTrainingSlotDtos(List<CreateTrainingSlotDto> createTrainingSlotDtos) {
        this.createTrainingSlotDtos = createTrainingSlotDtos;
    }
}
