package br.com.fiap.sentinel_api.entity;

import br.com.fiap.sentinel_api.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "T_GS_USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    @Size(min = 3, message = "e-mail must have at least 3 characters")
    @NotBlank(message = "e-mail cannot be blank")
    @NotNull(message = "e-mail cannot be null")
    @Column(name = "email_user", nullable = false, unique = true)
    private String email;

    @Size(min = 8, message = "password must have at least 8 characters")
    @NotBlank(message = "password cannot be blank")
    @NotNull(message = "password cannot be null")
    @Column(name = "password_user", nullable = false)
    private String password;


    @NotNull(message = "role cannot be null")
    @Column(name = "role_user", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
