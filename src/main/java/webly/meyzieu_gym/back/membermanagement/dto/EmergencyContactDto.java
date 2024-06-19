package webly.meyzieu_gym.back.membermanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EmergencyContactDto {
    
    @NotBlank(message = "Le prénom ne peut pas être vide")
    @Size(min = 3, max = 100, message = "La taille du prénom n'est pas valide")
    private String firstname;

    @NotBlank(message = "Le nom de famille ne peut pas être vide")
    @Size(min = 3, max = 100, message = "La taille du nom de famille n'est pas valide")
    private String lastname;

    @NotBlank(message = "La relation avec le membre ne peut pas être vide")
    @Pattern(regexp = "father|mother|other", message = "La relation envoyée au membre n'est pas correcte")
    private String relationToMember;

    @NotBlank(message = "Le numéro de téléphone ne peut pas être vide")
    @Size(min = 10, max = 20, message = "La taille du numéro de téléphone doit être comprise entre 10 et 20 caractères")
    private String phoneNumber;

    public EmergencyContactDto() {
    }

    public EmergencyContactDto(String firstname, String lastname, String relationToMember, String phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.relationToMember = relationToMember;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRelationToMember() {
        return this.relationToMember;
    }

    public void setRelationToMember(String relationToMember) {
        this.relationToMember = relationToMember;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
