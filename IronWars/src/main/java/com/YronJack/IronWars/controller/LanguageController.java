package com.YronJack.IronWars.controller;

import com.YronJack.IronWars.dto.language.LanguageRequestDTO;
import com.YronJack.IronWars.dto.language.LanguageResponseDTO;
import com.YronJack.IronWars.model.Language;
import com.YronJack.IronWars.service.interfaces.LanguageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguageController {
    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping
    public List<LanguageResponseDTO> getAll() {
        return languageService.getAll()
                .stream()
                .map(language -> {
                    LanguageResponseDTO languageResponseDTO = new LanguageResponseDTO();
                    languageResponseDTO.setId(language.getId());
                    languageResponseDTO.setName(language.getName());
                    languageResponseDTO.setDomain(language.getDomain());
                    return languageResponseDTO;
                })
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LanguageResponseDTO create(@RequestBody LanguageRequestDTO languageRequestDTO) {
        Language lang =new Language();
        lang.setName(languageRequestDTO.getName());
        lang.setDomain(languageRequestDTO.getDomain());
        Language saved =languageService.create(lang);

        LanguageResponseDTO languageResponseDTO = new LanguageResponseDTO();
        languageResponseDTO.setId(saved.getId());
        languageResponseDTO.setName(saved.getName());
        languageResponseDTO.setDomain(saved.getDomain());
        return languageResponseDTO;
    }
}
