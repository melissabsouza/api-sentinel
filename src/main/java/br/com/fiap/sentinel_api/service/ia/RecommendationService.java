package br.com.fiap.sentinel_api.service.ia;


import br.com.fiap.sentinel_api.dto.RecommendationRequestDTO;
import br.com.fiap.sentinel_api.entity.Shelter;
import br.com.fiap.sentinel_api.repository.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final GeminiService geminiService;
    private final ShelterRepository shelterRepository;

    public String recommendShelter(RecommendationRequestDTO request) {
        List<Shelter> shelters = shelterRepository.findAll();

        StringBuilder prompt = new StringBuilder("Baseado nas seguintes necessidades, recomende abrigos:\n\n");
        prompt.append("Cidade: ").append(request.getCity()).append("\n");
        if (request.isAccessibility()) prompt.append("- Precisa de acessibilidade\n");
        if (request.isAcceptPets()) prompt.append("- Tem pets\n");
        if (request.isWithChildren()) prompt.append("- Está com crianças\n");
        if (request.isElderly()) prompt.append("- Está com idosos\n");
        if (request.isEmergency()) prompt.append("- É uma emergência\n");

        prompt.append("\nAbaixo os abrigos disponíveis:\n");

        String abrigos = shelters.stream()
                .map(s -> String.format(
                        "- %s, Cidade: %s, Recursos: %s, Capacidade: %d/%d",
                        s.getName(),
                        s.getAddress().getCity(),
                        s.getAvailableResources(),
                        s.getCurrentCapacity(),
                        s.getTotalCapacity()
                ))
                .collect(Collectors.joining("\n"));

        prompt.append(abrigos);
        prompt.append("\n\nResponda em português com os abrigos mais recomendados e explique por quê, brevemente, no máximo de 500 caracteres.");

        return geminiService.chamarGemini(prompt.toString());
    }
}

