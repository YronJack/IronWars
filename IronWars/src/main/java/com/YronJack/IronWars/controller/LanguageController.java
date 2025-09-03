package com.YronJack.IronWars.controller;

import com.YronJack.IronWars.dto.language.LanguageRequestDTO;
import com.YronJack.IronWars.dto.language.LanguageResponseDTO;
import com.YronJack.IronWars.model.Language;
import com.YronJack.IronWars.service.interfaces.LanguageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/languages")
@Tag(name = "Languages", description = "Operations related to programming languages")
public class LanguageController {
    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @Operation(summary = "Get all languages")
    @ApiResponse(responseCode = "200", description = "List of all languages")
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

    @Operation(summary = "Create a new language")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Language created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LanguageResponseDTO create(@RequestBody LanguageRequestDTO languageRequestDTO) {
        Language lang = new Language();
        lang.setName(languageRequestDTO.getName());
        lang.setDomain(languageRequestDTO.getDomain());
        Language saved = languageService.create(lang);

        LanguageResponseDTO languageResponseDTO = new LanguageResponseDTO();
        languageResponseDTO.setId(saved.getId());
        languageResponseDTO.setName(saved.getName());
        languageResponseDTO.setDomain(saved.getDomain());
        return languageResponseDTO;
    }
}