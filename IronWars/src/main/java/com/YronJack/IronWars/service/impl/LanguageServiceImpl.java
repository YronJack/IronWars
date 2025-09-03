package com.YronJack.IronWars.service.impl;

import com.YronJack.IronWars.enums.ExperienceLevel;
import com.YronJack.IronWars.model.Language;
import com.YronJack.IronWars.model.Student;
import com.YronJack.IronWars.model.Teacher;
import com.YronJack.IronWars.repository.LanguageRepository;
import com.YronJack.IronWars.repository.StudentRepository;
import com.YronJack.IronWars.repository.TeacherRepository;
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
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    
    public LanguageServiceImpl(LanguageRepository languageRepository, 
                              TeacherRepository teacherRepository,
                              StudentRepository studentRepository) {
        this.languageRepository = languageRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
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
    
    @Override
    public List<Teacher> getExpertsByLanguage(Long languageId) {
        // Validate that the language exists
        getById(languageId); // This will throw if language doesn't exist
        return teacherRepository.findByLanguages_Id(languageId);
    }
    
    @Override
    public List<Student> getStudentsByExperienceLevel(ExperienceLevel level) {
        return studentRepository.findByExperienceLevel(level);
    }
}
