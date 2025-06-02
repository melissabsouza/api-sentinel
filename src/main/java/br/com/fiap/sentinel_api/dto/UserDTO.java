package br.com.fiap.sentinel_api.dto;

import br.com.fiap.sentinel_api.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    @Size(min = 3, message = "e-mail must have at least 3 characters")
    @NotBlank(message = "e-mail cannot be blank")
    @NotNull(message = "e-mail cannot be null")
    private String email;

    @Size(min = 8, message = "password must have at least 8 characters")
    @NotBlank(message = "password cannot be blank")
    @NotNull(message = "password cannot be null")
    private String password;

    @NotBlank(message = "role cannot be blank")
    @NotNull(message = "role cannot be null")
    private UserRole role;
}
