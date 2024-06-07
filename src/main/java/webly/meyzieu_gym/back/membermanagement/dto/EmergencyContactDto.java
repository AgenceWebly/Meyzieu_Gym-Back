package webly.meyzieu_gym.back.membermanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class EmergencyContactDto {
    
    @NotBlank
    @Size(min = 3, max = 100)
    private String firstname;

    @NotBlank
    @Size(min = 3, max = 100)
    private String lastname;

    @NotBlank
    @Pattern(regexp = "parent|grand-parent|relative|other", message = "The sent relation to member isn't right")
    private String relationToMember;

    @NotBlank
    @Size(min = 10, max = 20)
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
