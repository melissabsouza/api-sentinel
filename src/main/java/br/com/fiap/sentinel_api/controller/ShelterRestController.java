package br.com.fiap.sentinel_api.controller;

import br.com.fiap.sentinel_api.dto.ShelterDTO;
import br.com.fiap.sentinel_api.exception.ItemNotFoundException;
import br.com.fiap.sentinel_api.service.ShelterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shelters")
@AllArgsConstructor
public class ShelterRestController {

    private final ShelterService shelterService;

    @GetMapping
    public ResponseEntity<List<ShelterDTO>> getAll() {
        List<ShelterDTO> shelters = shelterService.findAll();
        return ResponseEntity.ok(shelters);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShelterDTO> getById(@PathVariable Long id) {
        try {
            ShelterDTO shelter = shelterService.findById(id);
            return ResponseEntity.ok(shelter);
        } catch (ItemNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ShelterDTO> create(@Validated @RequestBody ShelterDTO shelterDTO) {
        ShelterDTO createdShelter = shelterService.saveShelter(shelterDTO);
        return ResponseEntity.status(201).body(createdShelter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShelterDTO> update(@PathVariable Long id, @Validated @RequestBody ShelterDTO shelterDTO) {
        try {
            shelterDTO.setId(id);  // garante que atualiza o certo
            ShelterDTO updated = shelterService.saveShelter(shelterDTO);
            return ResponseEntity.ok(updated);
        } catch (ItemNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            shelterService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (ItemNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

