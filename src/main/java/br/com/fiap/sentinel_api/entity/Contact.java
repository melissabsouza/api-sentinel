package br.com.fiap.sentinel_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "T_GS_CONTACT")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contact")
    private Long id;

    @Size(min = 3, message = "e-mail must have at least 3 characters")
    @NotBlank(message = "e-mail cannot be blank")
    @NotNull(message = "e-mail cannot be null")
    @Column(name = "email_contact", nullable = false)
    private String email;

    @Pattern(regexp = "\\d{2}\\s?(9\\d{4}-?\\d{4}|\\d{4}-?\\d{4})",
            message = "Use XX 9XXXX-XXXX, XX 9XXXX XXXX or XX 9XXXXXXXX")
    @NotBlank(message = "phone number cannot be blank")
    @NotNull(message = "phone number cannot be null")
    @Column(name = "contact_number", length = 15, nullable = false)
    private String phone;
}
