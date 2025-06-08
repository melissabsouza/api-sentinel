package br.com.fiap.sentinel_api.controller;

import br.com.fiap.sentinel_api.dto.RecommendationRequestDTO;
import br.com.fiap.sentinel_api.service.ia.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recommendation")
public class RecommendationController {

    private final RecommendationService recomendacaoService;

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("request", new RecommendationRequestDTO());
        return "recommendation";
    }

    @PostMapping
    public String processForm(@ModelAttribute("request") RecommendationRequestDTO request, Model model) {
        String respostaIA = recomendacaoService.recommendShelter(request);
        model.addAttribute("respostaIA", respostaIA);
        return "recommendation";
    }
}

