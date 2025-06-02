package br.com.fiap.sentinel_api;

import br.com.fiap.sentinel_api.controller.ShelterController;
import br.com.fiap.sentinel_api.dto.AddressDTO;
import br.com.fiap.sentinel_api.dto.ContactDTO;
import br.com.fiap.sentinel_api.dto.ShelterDTO;
import br.com.fiap.sentinel_api.enums.ShelterStatus;
import br.com.fiap.sentinel_api.service.ShelterService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ShelterController.class)
class ShelterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShelterService shelterService;

    private ShelterDTO createSampleShelterDTO() {
        ShelterDTO shelterDTO = new ShelterDTO();
        shelterDTO.setId(1L);
        shelterDTO.setName("Shelter Teste");
        shelterDTO.setTotalCapacity(100);
        shelterDTO.setCurrentCapacity(50);
        shelterDTO.setAvailableResources("Food, Water, Medicine");
        shelterDTO.setStatus(ShelterStatus.CLOSED);
        shelterDTO.setLastUpdate(LocalDateTime.now());

        AddressDTO address = new AddressDTO();
        address.setStreet("Rua Teste");
        address.setCity("São Paulo");
        address.setCep("01234-567");
        shelterDTO.setAddress(address);

        ContactDTO contact = new ContactDTO();
        contact.setEmail("contato@shelter.com");
        contact.setPhone("11999999999");
        shelterDTO.setContact(contact);

        shelterDTO.setUserEmail("user@shelter.com");

        return shelterDTO;
    }

    @Test
    @DisplayName("GET /shelters")
    void testListShelters() throws Exception {
        ShelterDTO s1 = createSampleShelterDTO();
        ShelterDTO s2 = createSampleShelterDTO();
        s2.setId(2L);
        s2.setName("Outro Shelter");

        when(shelterService.findAll()).thenReturn(Arrays.asList(s1, s2));

        mockMvc.perform(get("/shelters"))
                .andExpect(status().isOk())
                .andExpect(view().name("shelters/list"))
                .andExpect(model().attributeExists("shelters"))
                .andExpect(model().attribute("shelters", Arrays.asList(s1, s2)));
    }

    @Test
    @DisplayName("GET /shelters/new")
    void testNewShelter() throws Exception {
        mockMvc.perform(get("/shelters/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("shelters/form"))
                .andExpect(model().attributeExists("shelter"));
    }

    @Test
    @DisplayName("POST /shelters")
    void testSaveShelterWithPrintErrors() throws Exception {
        ShelterDTO shelterMock = createSampleShelterDTO();

        when(shelterService.saveShelter(any(ShelterDTO.class))).thenReturn(shelterMock);

        MvcResult result = mockMvc.perform(post("/shelters")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "Sh")
                        .param("totalCapacity", "100")
                        .param("currentCapacity", "50")
                        .param("availableResources", "Fo")
                        .param("status", "CLOSED")
                        .param("address.street", "Rua Teste")
                        .param("address.city", "São Paulo")
                        .param("address.zipCode", "01234-567")
                        .param("contact.email", "contato@shelter.com")
                        .param("contact.phone", "11999999999")
                        .param("userEmail", "user@shelter.com")
                )
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("=== content ===");
        System.out.println(content);
    }

    @Test
    @DisplayName("POST /shelters")
    void testSaveShelterSucess() throws Exception {
        when(shelterService.saveShelter(any(ShelterDTO.class))).thenReturn(new ShelterDTO());

        mockMvc.perform(post("/shelters")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "Shelter Teste")
                        .param("totalCapacity", "100")
                        .param("currentCapacity", "50")
                        .param("availableResources", "Food, Water, Medicine")
                        .param("status", "OPEN")
                        .param("address.street", "Rua Teste")
                        .param("address.city", "São Paulo")
                        .param("address.cep", "01234-567")
                        .param("address.number", "44")
                        .param("address.district", "centro")
                        .param("address.state", "São Paulo")
                        .param("contact.email", "contato@shelter.com")
                        .param("contact.phone", "11999999999")
                        .param("userEmail", "user@shelter.com")
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/shelters"));

        verify(shelterService, times(1)).saveShelter(any(ShelterDTO.class));
    }


    @Test
    @DisplayName("POST /shelters com erro de validação deve voltar pro form")
    void testSaveShelterWithValidationError() throws Exception {
        mockMvc.perform(post("/shelters")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("totalCapacity", "100")
                )
                .andExpect(status().isOk())
                .andExpect(view().name("shelters/form"))
                .andExpect(model().attributeExists("shelter"));
    }


    @Test
    @DisplayName("GET /shelters/edit/{id}")
    void testEditShelter() throws Exception {
        ShelterDTO shelterDTO = createSampleShelterDTO();

        when(shelterService.findById(1L)).thenReturn(shelterDTO);

        mockMvc.perform(get("/shelters/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("shelters/form"))
                .andExpect(model().attribute("shelter", shelterDTO));
    }

    @Test
    @DisplayName("GET /shelters/delete/{id}")
    void testDeleteShelter() throws Exception {
        doNothing().when(shelterService).deleteById(1L);

        mockMvc.perform(get("/shelters/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/shelters"));

        verify(shelterService, times(1)).deleteById(1L);
    }
}
