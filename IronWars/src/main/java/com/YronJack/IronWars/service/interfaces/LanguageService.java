package com.YronJack.IronWars.service.interfaces;

import com.YronJack.IronWars.model.Language;

import java.util.List;

public interface LanguageService {
    Language getById (Long id);
    List<Language> getAll();
    Language create(Language language);
    Language update(Long id, Language update);
    void delete(Long id);
}
