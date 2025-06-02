package br.com.fiap.sentinel_api.service.mapper;

import br.com.fiap.sentinel_api.dto.ShelterDTO;
import br.com.fiap.sentinel_api.entity.Shelter;

public class ShelterMapper {
    public static ShelterDTO toDto(Shelter shelter){
        ShelterDTO shelterDTO = new ShelterDTO();
        shelterDTO.setId(shelter.getId());
        shelterDTO.setName(shelter.getName());
        shelterDTO.setStatus(shelter.getStatus());
        shelterDTO.setAvailableResources(shelter.getAvailableResources());
        shelterDTO.setCurrentCapacity(shelter.getCurrentCapacity());
        shelterDTO.setTotalCapacity(shelter.getTotalCapacity());
        shelterDTO.setLastUpdate(shelter.getLastUpdate());

        shelterDTO.setAddress(AddressMapper.toDto(shelter.getAddress()));
        shelterDTO.setContact(ContactMapper.toDto(shelter.getContact()));

        if(shelter.getUser() != null){
            shelterDTO.setUserEmail(shelter.getUser().getEmail());
        }
        return shelterDTO;
    }

    public static Shelter toEntity(ShelterDTO shelterDTO){
        Shelter shelter = new Shelter();
        shelter.setId(shelterDTO.getId());
        shelter.setName(shelterDTO.getName());
        shelter.setStatus(shelterDTO.getStatus());
        shelter.setAvailableResources(shelterDTO.getAvailableResources());
        shelter.setCurrentCapacity(shelterDTO.getCurrentCapacity());
        shelter.setTotalCapacity(shelterDTO.getTotalCapacity());
        shelter.setLastUpdate(shelterDTO.getLastUpdate());

        shelter.setAddress(AddressMapper.toEntity(shelterDTO.getAddress()));
        shelter.setContact(ContactMapper.toEntity(shelterDTO.getContact()));
        return shelter;

    }
}
