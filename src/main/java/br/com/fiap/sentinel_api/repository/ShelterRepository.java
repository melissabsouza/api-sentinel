package br.com.fiap.sentinel_api.repository;

import br.com.fiap.sentinel_api.entity.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {
}
