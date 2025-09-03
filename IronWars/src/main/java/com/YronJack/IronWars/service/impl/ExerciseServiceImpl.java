package com.YronJack.IronWars.service.impl;

import com.YronJack.IronWars.dto.exercise.ExerciseRequestDTO;
import com.YronJack.IronWars.dto.exercise.ExerciseResponseDTO;
import com.YronJack.IronWars.enums.ExperienceLevel;
import com.YronJack.IronWars.model.Exercise;
import com.YronJack.IronWars.model.Language;
import com.YronJack.IronWars.repository.ExerciseRepository;
import com.YronJack.IronWars.service.interfaces.ExerciseService;
import com.YronJack.IronWars.enums.Difficulty;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service
public class ExerciseServiceImpl implements ExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }
    //CRUD
    @Override
    public ExerciseResponseDTO createExercise(ExerciseRequestDTO request) {
        Exercise entity = toEntity(request);
        Exercise saved = exerciseRepository.save(entity);
        return ExerciseResponseDTO.fromEntity(saved);
    }

    @Override
    public ExerciseResponseDTO getExerciseById(Long id) {
        Exercise ex = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found"));
        return ExerciseResponseDTO.fromEntity(ex);
    }

    @Override
    public List<ExerciseResponseDTO> getAllExercises() {
        return exerciseRepository.findAll()
                .stream()
                .map(ExerciseResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public List<ExerciseResponseDTO> getExercisesByDifficulty(Difficulty difficulty) {
        return exerciseRepository.findByDifficulty(difficulty)
                .stream()
                .map(ExerciseResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public List<ExerciseResponseDTO> getExercisesByLanguage(Long languageId) {
        return exerciseRepository.findByLanguage_Id(languageId)
                .stream()
                .map(ExerciseResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public ExerciseResponseDTO updateExercise(Long id, ExerciseRequestDTO exerciseRequestDTO) {
        Exercise existing = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found"));

        //actualizar campos
        existing.setTitle(exerciseRequestDTO.getTitle());
        existing.setDescription(exerciseRequestDTO.getDescription());
        existing.setStarterCode(exerciseRequestDTO.getStarterCode());
        existing.setSolution(exerciseRequestDTO.getSolution());
        existing.setDifficulty(exerciseRequestDTO.getDifficulty());

        //reasignar lenguaje si cambia
        if (exerciseRequestDTO.getLanguageId() != null) {
            try {
                Language ref = entityManager.find(Language.class, exerciseRequestDTO.getLanguageId());
                ref.getId();
                existing.setLanguage(ref);
            } catch (EntityNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found");
            }
        }
        Exercise saved =exerciseRepository.save (existing);
        return ExerciseResponseDTO.fromEntity(saved);
    }

    @Override
    public void deleteExercise(Long id) {
        Exercise ex = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise not found"));
        exerciseRepository.delete(ex);
    }

    @Override
    public List<Exercise> getExercisesByDifficultyAndLanguageId(Difficulty difficulty, Long languageId) {
        return exerciseRepository.findByDifficultyAndLanguageId(difficulty, languageId);
    }
    //Random
    public List<Exercise> fillExamWithRandomExercises(ExperienceLevel experienceLevel, Long languageId) throws Exception {
        Difficulty difficulty = Difficulty.valueOf(experienceLevel.toString());
        List<Exercise> pool = getExercisesByDifficultyAndLanguageId(difficulty, languageId);

        if (pool.isEmpty()) {
            throw new Exception("No exercises found");
        }
        List<Exercise> poolCopy = new ArrayList<>(pool);
        List<Exercise> selected = new ArrayList<>();

        Random rand = new Random();

        int count = Math.min(10, poolCopy.size());
        for (int i = 0; i < count; i++) {
            int idx = rand.nextInt(poolCopy.size());
            selected.add(poolCopy.remove(idx));
        }
        return selected;
    }

    private Exercise toEntity(ExerciseRequestDTO dto) {
        Exercise e = new Exercise();
        e.setTitle(dto.getTitle());
        e.setDescription(dto.getDescription());
        e.setStarterCode(dto.getStarterCode());
        e.setSolution(dto.getSolution());
        e.setDifficulty(dto.getDifficulty());

        try {
            Language ref = entityManager.find(Language.class, dto.getLanguageId());
            ref.getId();
            e.setLanguage(ref);
        } catch (EntityNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Language not found");
        }
        return e;
    }
}