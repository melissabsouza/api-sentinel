package br.com.fiap.sentinel_api.service.mapper;

import br.com.fiap.sentinel_api.dto.ContactDTO;
import br.com.fiap.sentinel_api.entity.Contact;

public class ContactMapper {
    public static ContactDTO toDto(Contact contact){
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setId(contact.getId());
        contactDTO.setPhone(contact.getPhone());
        contactDTO.setEmail(contact.getEmail());
        return contactDTO;
    }

    public static Contact toEntity(ContactDTO contactDTO){
        Contact contact = new Contact();
        contact.setId(contactDTO.getId());
        contact.setPhone(contactDTO.getPhone());
        contact.setEmail(contactDTO.getEmail());
        return contact;
    }
}
