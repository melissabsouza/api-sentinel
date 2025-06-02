package br.com.fiap.sentinel_api.controller;


import br.com.fiap.sentinel_api.dto.ShelterDTO;
import br.com.fiap.sentinel_api.entity.Shelter;
import br.com.fiap.sentinel_api.exception.ItemNotFoundException;
import br.com.fiap.sentinel_api.service.ShelterService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@Controller
@AllArgsConstructor
@Log
@RequestMapping("/shelters")
public class ShelterController {

    private final ShelterService shelterService;

    @GetMapping
    public String listShelters(Model model){
        List<ShelterDTO> list = shelterService.findAll();
        model.addAttribute("shelters", list);
        return "shelters/list";
    }
    @GetMapping("/new")
    public String newShelter(Model model){
        ShelterDTO shelterDTO = new ShelterDTO();
        model.addAttribute("shelter", shelterDTO);
        return "shelters/form";
    }

    @PostMapping
    public String saveShelter(@Valid @ModelAttribute("shelter") ShelterDTO shelterDTO,
                              BindingResult bindingResults, Model model){
        if (bindingResults.hasErrors()) {
            bindingResults.getAllErrors().forEach(e -> log.info(e.toString()));
            model.addAttribute("shelter", shelterDTO);
            return "shelters/form";
        }

        try {
            shelterService.saveShelter(shelterDTO);
        } catch (RuntimeException e) {
            log.info("Erro: " + e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "shelters/form";
        }

        return "redirect:/shelters";
    }

    @GetMapping("/edit/{id}")
    public String editShelter(@PathVariable Long id, Model model) {
        ShelterDTO shelterDTO = shelterService.findById(id);
        model.addAttribute("shelter", shelterDTO);
        return "shelters/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteShelter(@PathVariable Long id, Model model){
        shelterService.deleteById(id);
        return "redirect:/shelters";
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public String handleItemNotFound(ItemNotFoundException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "shelters/form";
    }

    private MessageSource messageSource;

    @GetMapping("/test-locale")
    @ResponseBody
    public String testLocale(@RequestParam(name = "lang", required = false) String lang) {
        Locale locale = (lang != null) ? Locale.forLanguageTag(lang) : Locale.getDefault();
        String mensagem = messageSource.getMessage("hello.message", null, locale);
        return "Locale: " + locale + " -> Mensagem: " + mensagem;
    }

}
