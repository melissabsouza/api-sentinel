package br.com.fiap.sentinel_api.dto;


import br.com.fiap.sentinel_api.enums.ShelterStatus;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ShelterDTO {
    private Long id;

    @Size(min = 3, message = "name must have at least 3 characters")
    @NotBlank(message = "name cannot be blank")
    @NotNull(message = "name cannot be null")
    private String name;

    @NotNull(message = "total capacity cannot be null")
    private int totalCapacity;

    @NotNull(message = "current capacity cannot be null")
    private int currentCapacity;

    @Size(min = 3, message = "available resources must have at least 3 characters")
    @NotBlank(message = "available resources cannot be blank")
    @NotNull(message = "available resources cannot be null")
    private String availableResources;

    @NotNull(message = "status cannot be null")
    private ShelterStatus status;

    private LocalDateTime lastUpdate;

    @Valid
    private AddressDTO address;
    @Valid
    private ContactDTO contact;
    @Valid
    private String userEmail;
}
