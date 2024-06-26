package webly.meyzieu_gym.back.coursemanagement.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import webly.meyzieu_gym.back.registrationmanagement.entity.Registration;

@Entity
@Table(name = "course")
public class Course {

@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;

    @Column(name = "course_name", nullable = false, length = 30)
    private String courseName;
    
    @Column(name = "registration_start_date", nullable = false)
    private LocalDateTime registrationStartDate;

    @Column(name = "registration_end_date", nullable = false)
    private LocalDateTime registrationEndDate;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "max_members", nullable = false)
    private Integer maxMembers;

    @Column(name = "min_age", nullable = false)
    private Integer minAge;

    @Column(name = "max_age", nullable = false)
    private Integer maxAge;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingSlot> trainingSlots;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Registration> registrations;

    public Course() {
    }

    public Course(
            Season season,
            Program program,
            String courseName,
            LocalDateTime registrationStartDate,
            LocalDateTime registrationEndDate,
            BigDecimal price,
            Integer maxMembers,
            Integer minAge,
            Integer maxAge) {
        this.season = season;
        this.program = program;
        this.courseName = courseName;
        this.registrationStartDate = registrationStartDate;
        this.registrationEndDate = registrationEndDate;
        this.price = price;
        this.maxMembers = maxMembers;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Season getSeason() {
        return this.season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Program getProgram() {
        return this.program;
    }

    public void setProgram(Program program) {
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

    public List<TrainingSlot> getTrainingSlots() {
        return this.trainingSlots;
    }

    public void setTrainingSlots(List<TrainingSlot> trainingSlots) {
        this.trainingSlots = trainingSlots;
    }

    public List<Registration> getRegistrations() {
        return this.registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }
}
