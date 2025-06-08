package br.com.fiap.sentinel_api.repository;

import br.com.fiap.sentinel_api.entity.Shelter;
import br.com.fiap.sentinel_api.enums.ShelterStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {
}