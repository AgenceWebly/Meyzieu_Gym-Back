package webly.meyzieu_gym.back.usermanagement.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserProfileDto {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;

    @NotBlank(message = "The phone number is mandatory")
    @Size(min = 3, max = 20, message = "The phone number format isn't correct!")
    private String phoneNumber;

    @NotBlank(message = "The adress is mandatory")
    @Size(min = 3, max = 255, message = "The address format isn't correct!")
    private String address;

    @NotBlank(message = "The occupation is mandatory")
    @Size(min = 3, message = "The occupation format isn't correct!")
    private String occupation;

    public UserProfileDto() {
    }

    public UserProfileDto(Long id, String firstname, String lastname, String email, String phoneNumber, String address, String occupation) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.occupation = occupation;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOccupation() {
        return this.occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

}
