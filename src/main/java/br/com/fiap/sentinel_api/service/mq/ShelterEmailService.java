package br.com.fiap.sentinel_api.service.mq;

import br.com.fiap.sentinel_api.entity.Shelter;
import br.com.fiap.sentinel_api.repository.ShelterRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShelterEmailService {
    private final ShelterRepository shelterRepository;
    private final EmailProducer emailProducer;


    @Transactional
    public Shelter signShelter(Shelter shelter) {
        shelterRepository.save(shelter);

        String emailClinica = shelter.getUser().getEmail();

        String assunto = "Now you are responsible for a shelter!";
        String mensagem = "New shelter registered in your account:\n"
                + "Name: " + shelter.getName() + "\n"
                + "Phone: " + shelter.getContact().getPhone() + "\n"
                + "Located in: " + shelter.getAddress().getCity() + ", "
                + shelter.getAddress().getState();

        EmailPayload payload = new EmailPayload();
        payload.setTo(emailClinica);
        payload.setSubject(assunto);
        payload.setBody(mensagem);

        emailProducer.sendEmail(payload);
        return shelter;
    }
}
