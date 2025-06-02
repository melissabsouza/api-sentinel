package br.com.fiap.sentinel_api;


import br.com.fiap.sentinel_api.dto.ShelterDTO;
import br.com.fiap.sentinel_api.entity.Address;
import br.com.fiap.sentinel_api.entity.Contact;
import br.com.fiap.sentinel_api.entity.Shelter;
import br.com.fiap.sentinel_api.entity.User;
import br.com.fiap.sentinel_api.enums.ShelterStatus;
import br.com.fiap.sentinel_api.enums.UserRole;
import br.com.fiap.sentinel_api.repository.ShelterRepository;
import br.com.fiap.sentinel_api.service.ShelterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ShelterServiceTest {

    @InjectMocks
    private ShelterService shelterService;

    @Mock
    private ShelterRepository shelterRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Shelter getShelter(Long id) {
        Shelter shelter = new Shelter();
        shelter.setId(id);
        shelter.setName("Shelter Teste");
        shelter.setStatus(ShelterStatus.valueOf("OPEN"));
        shelter.setAvailableResources("Água, Comida");
        shelter.setCurrentCapacity(10);
        shelter.setTotalCapacity(50);
        shelter.setLastUpdate(LocalDateTime.now());

        Address address = new Address();
        address.setId(1L);
        address.setCity("Cidade");
        address.setCep("12345-678");
        address.setState("Estado");
        address.setNumber(100);
        address.setDistrict("Bairro");
        address.setStreet("Rua XPTO");
        shelter.setAddress(address);

        Contact contact = new Contact();
        contact.setId(1L);
        contact.setPhone("123456789");
        contact.setEmail("contato@shelter.com");
        shelter.setContact(contact);

        User user = new User();
        user.setId(1L);
        user.setEmail("admin@shelter.com");
        user.setPassword("123456");
        user.setRole(UserRole.valueOf("ADMIN"));
        shelter.setUser(user);

        return shelter;
    }

    @Test
    void returnShelterById() {
        Long id = 1L;
        Shelter shelter = getShelter(id);

        when(shelterRepository.findById(id)).thenReturn(Optional.of(shelter));

        ShelterDTO dto = shelterService.findById(id);

        assertNotNull(dto);
        assertEquals("Shelter Teste", dto.getName());
        assertEquals("admin@shelter.com", dto.getUserEmail());
    }

    @Test
    void saveNewShelter() {
        ShelterDTO shelterDTO = new ShelterDTO();
        shelterDTO.setName("Novo Shelter");
        shelterDTO.setStatus(ShelterStatus.valueOf("OPEN"));
        shelterDTO.setAvailableResources("Cobertores");
        shelterDTO.setCurrentCapacity(5);
        shelterDTO.setTotalCapacity(20);
        shelterDTO.setAddress(new br.com.fiap.sentinel_api.dto.AddressDTO());
        shelterDTO.setContact(new br.com.fiap.sentinel_api.dto.ContactDTO());

        Shelter shelter = getShelter(1L);
        when(shelterRepository.save(any(Shelter.class))).thenReturn(shelter);

        ShelterDTO result = shelterService.saveShelter(shelterDTO);

        assertNotNull(result);
        assertEquals("Shelter Teste", result.getName());  // Por causa do mock que retorna shelter
    }

    @Test
    void returnShelterDTOList() {
        Shelter shelter = new Shelter();
        shelter.setId(1L);
        shelter.setName("Shelter Teste");

        Address address = new Address();
        address.setId(1L);
        address.setCity("Cidade");
        address.setCep("12345-678");
        address.setState("Estado");
        address.setNumber(100);
        address.setDistrict("Bairro");
        address.setStreet("Rua XPTO");
        shelter.setAddress(address);

        Contact contact = new Contact();
        contact.setId(1L);
        contact.setPhone("123456789");
        contact.setEmail("contato@shelter.com");
        shelter.setContact(contact);

        User user = new User();
        user.setId(1L);
        user.setEmail("admin@shelter.com");
        user.setPassword("123456");
        user.setRole(UserRole.valueOf("ADMIN"));
        shelter.setUser(user);

        when(shelterRepository.findAll()).thenReturn(Collections.singletonList(shelter));

        List<ShelterDTO> result = shelterService.findAll();

        assertEquals(1, result.size());
        assertEquals("Shelter Teste", result.get(0).getName());
        assertEquals("admin@shelter.com", result.get(0).getUserEmail());
        assertNotNull(result.get(0).getAddress());
        assertNotNull(result.get(0).getContact());
    }

    @Test
    void deleteShelterById() {
        Long id = 1L;

        doNothing().when(shelterRepository).deleteById(id);

        shelterService.deleteById(id);

        verify(shelterRepository, times(1)).deleteById(id);
    }

    @Test
    void exceptionWhenShelterNotFound() {
        Long id = 1L;

        when(shelterRepository.findById(id)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> shelterService.findById(id));

        assertEquals("id not found", ex.getMessage());
    }

}

