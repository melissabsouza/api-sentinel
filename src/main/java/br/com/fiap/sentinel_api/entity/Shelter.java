package br.com.fiap.sentinel_api.entity;

import br.com.fiap.sentinel_api.enums.ShelterStatus;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
@Data
@ToString
@Entity
@Table(name = "T_GS_SHELTER")
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_shelter")
    private Long id;

    @Size(min = 3, message = "name must have at least 3 characters")
    @NotBlank(message = "name cannot be blank")
    @NotNull(message = "name cannot be null")
    @Column(name = "name_shelter", nullable = false)
    private String name;

    @NotNull(message = "total capacity cannot be null")
    @Column(name = "total_capacity", nullable = false)
    private int totalCapacity;

    @NotNull(message = "current capacity cannot be null")
    @Column(name = "current_capacity", nullable = false)
    private int currentCapacity;

    @Size(min = 3, message = "available resources must have at least 3 characters")
    @NotBlank(message = "available resources cannot be blank")
    @NotNull(message = "available resources cannot be null")
    @Column(name = "available_resources", nullable = false)
    private String availableResources;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "status cannot be null")
    @Column(name = "status_shelter", nullable = false)
    private ShelterStatus status;

    @Column(name = "update_date_shelter", nullable = false)
    private LocalDateTime lastUpdate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address", referencedColumnName = "id_address", nullable = false)
    @Valid
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_contact", referencedColumnName = "id_contact", nullable = false)
    @Valid
    private Contact contact;

    @OneToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    @Valid
    private User user;

}
