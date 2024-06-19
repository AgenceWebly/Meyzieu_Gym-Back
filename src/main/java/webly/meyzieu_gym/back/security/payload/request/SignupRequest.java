package webly.meyzieu_gym.back.security.payload.request;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignupRequest {

    @NotBlank(message = "Le prénom ne peut pas être vide")
    @Size(min = 3, max = 100, message = "La taille du prénom n'est pas valide")
    private String firstname;

    @NotBlank(message = "Le nom de famille ne peut pas être vide")
    @Size(min = 3, max = 100, message = "La taille du nom de famille n'est pas valide")
    private String lastname;

    @NotBlank(message = "L'adresse e-mail ne peut pas être vide")
    @Size(max = 50, message = "La taille de l'adresse e-mail n'est pas valide")
    @Email(message = "L'adresse e-mail doit être valide")
    private String email;

    @NotBlank(message = "Le mot de passe ne peut pas être vide")
    @Size(min = 8, max = 40, message = "La taille du mot de passe doit être comprise entre 8 et 40 caractères")
    private String password;

    @NotBlank(message = "Le numéro de téléphone ne peut pas être vide")
    @Size(min = 3, max = 20, message = "La taille du numéro de téléphone n'est pas valide")
    private String phoneNumber;

    @NotBlank(message = "L'adresse ne peut pas être vide")
    @Size(min = 15, max = 255, message = "La taille de l'adresse n'est pas valide")
    private String address;

    private String occupation;

    private Set<String> role;

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

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

}
