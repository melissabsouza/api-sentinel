package br.com.fiap.sentinel_api.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressDTO {
    private Long id;

    @Size(min = 5, message = "The street must have at least 5 characters")
    @NotBlank(message = "The street cannot be blank")
    @NotNull(message = "The street cannot be null")
    private String street;

    @NotNull(message = "number cannot be null")
    private int number;

    @Size(min = 3, message = "district must have at least 3 characters")
    @NotBlank(message = "district cannot be blank")
    @NotNull(message = "district cannot be null")
    private String district;

    @Size(min = 3, message = "must have at least 3 characters")
    @NotBlank(message = "cannot be blank")
    private String city;

    @Size(min = 3, message = "state must have at least 3 characters")
    @NotBlank(message = "state cannot be blank")
    private String state;


    @Pattern(regexp = "\\d{5}-?\\d{3}",
            message = "Use XXXXX-XXX")
    @NotNull(message = "cep cannot be null")
    private String cep;
}
