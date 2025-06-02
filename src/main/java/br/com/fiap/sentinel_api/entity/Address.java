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
@Table(name = "T_GS_ADDRESS")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private Long id;

    @Size(min = 5, message = "The street must have at least 5 characters")
    @NotBlank(message = "The street cannot be blank")
    @NotNull(message = "The street cannot be null")
    @Column(name = "street_address", length = 100, nullable = false)
    private String street;

    @NotNull(message = "number cannot be null")
    @Column(name = "number_address", nullable = false)
    private int number;

    @Size(min = 3, message = "district must have at least 3 characters")
    @NotBlank(message = "district cannot be blank")
    @NotNull(message = "disctrict cannot be null")
    @Column(name = "district_address", length = 30, nullable = false)
    private String district;

    @Size(min = 3, message = "must have at least 3 characters")
    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    @Column(name = "city_address", length = 30, nullable = false)
    private String city;

    @Size(min = 3, message = "state must have at least 3 characters")
    @NotBlank(message = "state cannot be blank")
    @NotNull(message = "state cannot be null")
    @Column(name = "state_address", length = 30, nullable = false)
    private String state;

    @Pattern(regexp = "\\d{5}-?\\d{3}",
            message = "Use XXXXX-XXX")
    @NotNull(message = "cep cannot be null")
    @Column(name = "cep_address", nullable = false)
    private String cep;
}
