package com.YronJack.IronWars.service.impl;

import com.YronJack.IronWars.model.Language;
import com.YronJack.IronWars.repository.LanguageRepository;
import com.YronJack.IronWars.service.interfaces.LanguageService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional

public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;
    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    @Transactional
    public Language getById (Long id) {
        return languageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found"));
    }

    @Override
    @Transactional
    public List<Language> getAll() {
        return languageRepository.findAll();
    }
    @Override
    public Language create(Language language) {
        return languageRepository.save(language);
    }

    @Override
    public Language update(Long id, Language language) {
        Language existing = getById(id);
        existing.setName(language.getName());
        existing.setDomain(language.getDomain());
        return languageRepository.save(existing);
    }
    @Override
    public void delete(Long id) {
        Language existing = getById(id);
        languageRepository.delete(existing);
    }
}
