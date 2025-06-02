package br.com.fiap.sentinel_api.service;

import br.com.fiap.sentinel_api.dto.ShelterDTO;
import br.com.fiap.sentinel_api.entity.Address;
import br.com.fiap.sentinel_api.entity.Contact;
import br.com.fiap.sentinel_api.entity.Shelter;
import br.com.fiap.sentinel_api.entity.User;
import br.com.fiap.sentinel_api.exception.ItemNotFoundException;
import br.com.fiap.sentinel_api.repository.ShelterRepository;
import br.com.fiap.sentinel_api.repository.UserRepository;
import br.com.fiap.sentinel_api.service.mapper.AddressMapper;
import br.com.fiap.sentinel_api.service.mapper.ContactMapper;
import br.com.fiap.sentinel_api.service.mapper.ShelterMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShelterService {

    private ShelterRepository shelterRepository;
    private UserRepository userRepository;


    public ShelterDTO saveShelter(ShelterDTO shelterDTO){
        Shelter shelter;

        if(shelterDTO.getId() == null){
            shelter = new Shelter();
        } else{
            shelter = shelterRepository.findById(shelterDTO.getId())
                    .orElseThrow(() -> new ItemNotFoundException("Shelter not found"));
        }

        shelter.setName(shelterDTO.getName());
        shelter.setStatus(shelterDTO.getStatus());
        shelter.setAvailableResources(shelterDTO.getAvailableResources());
        shelter.setCurrentCapacity(shelterDTO.getCurrentCapacity());
        shelter.setTotalCapacity(shelterDTO.getTotalCapacity());
        shelter.setLastUpdate(LocalDateTime.now());

        if(shelterDTO.getUserEmail() != null){
            User user = userRepository.findByEmail(shelterDTO.getUserEmail())
                    .orElseThrow(() -> new ItemNotFoundException("User not found with email: " + shelterDTO.getUserEmail()));
            shelter.setUser(user);
        }

        Address address = AddressMapper.toEntity(shelterDTO.getAddress());
        Contact contact = ContactMapper.toEntity(shelterDTO.getContact());

        shelter.setAddress(address);
        shelter.setContact(contact);

        shelter = shelterRepository.save(shelter);
        return ShelterMapper.toDto(shelter);
    }


    public List<ShelterDTO> findAll(){
        List<Shelter> list = shelterRepository.findAll();
        List<ShelterDTO> dtos = list.stream().map(ShelterMapper::toDto).toList();
        return dtos;
    }
    public void deleteById(Long id){
        shelterRepository.deleteById(id);
    }

    public ShelterDTO findById(Long id){
        Optional<Shelter> byId = shelterRepository.findById(id);
        if(byId.isPresent()){
            return ShelterMapper.toDto(byId.get());
        }
        throw new RuntimeException("id not found");
    }
}
