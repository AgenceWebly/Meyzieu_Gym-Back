package webly.meyzieu_gym.back.registrationmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UpdateHealthCertificateDto {
    
    @NotNull
    private Long id;

    @NotNull
    private boolean isHealthCertificateRequired;

    @NotBlank
    private String healthCertificateFileUrl;

    public UpdateHealthCertificateDto() {
    }

    public UpdateHealthCertificateDto(Long id, boolean isHealthCertificateRequired, String healthCertificateFileUrl) {
        this.id = id;
        this.isHealthCertificateRequired = isHealthCertificateRequired;
        this.healthCertificateFileUrl = healthCertificateFileUrl;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isHealthCertificateRequired() {
        return this.isHealthCertificateRequired;
    }

    public void setHealthCertificateRequired(boolean isHealthCertificateRequired) {
        this.isHealthCertificateRequired = isHealthCertificateRequired;
    }

    public String getHealthCertificateFileUrl() {
        return this.healthCertificateFileUrl;
    }

    public void setHealthCertificateFileUrl(String healthCertificateFileUrl) {
        this.healthCertificateFileUrl = healthCertificateFileUrl;
    }

}
