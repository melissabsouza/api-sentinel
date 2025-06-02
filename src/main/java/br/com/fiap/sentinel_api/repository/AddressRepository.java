package br.com.fiap.sentinel_api.repository;

import br.com.fiap.sentinel_api.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
