package webly.meyzieu_gym.back.programmanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "program")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", nullable = false, length = 255)
    private String description;
    
    @Column(name = "is_including_competition", nullable = false)
    private boolean isIncludingCompetition;

    public Program() {
    }

    public Program(String name, String description, boolean isIncludingCompetition) {
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

    public boolean isIncludingCompetition() {
        return this.isIncludingCompetition;
    }

    public void setIncludingCompetition(boolean isIncludingCompetition) {
        this.isIncludingCompetition = isIncludingCompetition;
    }
}
