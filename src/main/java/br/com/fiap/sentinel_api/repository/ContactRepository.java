package br.com.fiap.sentinel_api.repository;

import br.com.fiap.sentinel_api.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
