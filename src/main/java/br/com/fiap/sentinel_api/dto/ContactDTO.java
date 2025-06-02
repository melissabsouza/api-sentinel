package br.com.fiap.sentinel_api.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContactDTO {
    private Long id;

    @Size(min = 3, message = "e-mail must have at least 3 characters")
    @NotBlank(message = "e-mail cannot be blank")
    @NotNull(message = "e-mail cannot be null")
    private String email;

    @Pattern(regexp = "\\d{2}\\s?(9\\d{4}-?\\d{4}|\\d{4}-?\\d{4})",
            message = "Use XX 9XXXX-XXXX, XX 9XXXX XXXX or XX 9XXXXXXXX")
    @NotBlank(message = "phone number cannot be blank")
    @NotNull(message = "phone number cannot be null")
    private String phone;
}
