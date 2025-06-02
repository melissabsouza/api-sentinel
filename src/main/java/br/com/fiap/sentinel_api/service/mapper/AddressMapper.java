package br.com.fiap.sentinel_api.service.mapper;

import br.com.fiap.sentinel_api.dto.AddressDTO;
import br.com.fiap.sentinel_api.entity.Address;

public class AddressMapper {

    public static AddressDTO toDto(Address address){
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setCity(address.getCity());
        addressDTO.setCep(address.getCep());
        addressDTO.setState(address.getState());
        addressDTO.setNumber(address.getNumber());
        addressDTO.setDistrict(address.getDistrict());
        addressDTO.setStreet(address.getStreet());
        return addressDTO;
    }

    public static Address toEntity(AddressDTO addressDTO){
        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setCity(addressDTO.getCity());
        address.setCep(addressDTO.getCep());
        address.setState(addressDTO.getState());
        address.setNumber(addressDTO.getNumber());
        address.setDistrict(addressDTO.getDistrict());
        address.setStreet(addressDTO.getStreet());
        return address;
    }
}
