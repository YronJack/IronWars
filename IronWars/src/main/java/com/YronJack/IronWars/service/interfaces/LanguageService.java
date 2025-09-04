package com.YronJack.IronWars.service.interfaces;

import com.YronJack.IronWars.enums.ExperienceLevel;
import com.YronJack.IronWars.model.Language;
import com.YronJack.IronWars.model.Student;
import com.YronJack.IronWars.model.Teacher;

import java.util.List;

public interface LanguageService {
    Language getById (Long id);
    List<Language> getAll();
    Language create(Language language);
    Language update(Long id, Language update);
    void delete(Long id);
    
    // Methods for testing expert listing and student enrollment by level
    List<Teacher> getExpertsByLanguage(Long languageId);
    List<Student> getStudentsByExperienceLevel(ExperienceLevel level);
}
