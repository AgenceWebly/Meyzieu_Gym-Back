package webly.meyzieu_gym.back.registrationmanagement.dto;

import java.util.Optional;

public class UpdateRegistrationDto {
    
    private Optional<Boolean> isHealthCertificateRequired = Optional.empty();
    private Optional<String> healthCertificateFileUrl = Optional.empty();
    private Optional<String> paymentMethod = Optional.empty();
    private Optional<String> paymentStatus = Optional.empty();
    private Optional<String> registrationStatus = Optional.empty();

    public UpdateRegistrationDto() {
    }

    public UpdateRegistrationDto(
        Optional<Boolean> isHealthCertificateRequired, 
        Optional<String> healthCertificateFileUrl, 
        Optional<String> paymentMethod, 
        Optional<String> paymentStatus, 
        Optional<String> registrationStatus) {
            this.isHealthCertificateRequired = isHealthCertificateRequired;
            this.healthCertificateFileUrl = healthCertificateFileUrl;
            this.paymentMethod = paymentMethod;
            this.paymentStatus = paymentStatus;
            this.registrationStatus = registrationStatus;
    }

    public Optional<Boolean> getIsHealthCertificateRequired() {
        return this.isHealthCertificateRequired;
    }

    public void setIsHealthCertificateRequired(Optional<Boolean> isHealthCertificateRequired) {
        this.isHealthCertificateRequired = isHealthCertificateRequired;
    }

    public Optional<String> getHealthCertificateFileUrl() {
        return this.healthCertificateFileUrl;
    }

    public void setHealthCertificateFileUrl(Optional<String> healthCertificateFileUrl) {
        this.healthCertificateFileUrl = healthCertificateFileUrl;
    }

    public Optional<String> getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(Optional<String> paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Optional<String> getPaymentStatus() {
        return this.paymentStatus;
    }

    public void setPaymentStatus(Optional<String> paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Optional<String> getRegistrationStatus() {
        return this.registrationStatus;
    }

    public void setRegistrationStatus(Optional<String> registrationStatus) {
        this.registrationStatus = registrationStatus;
    }
}