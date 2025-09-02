package com.YronJack.IronWars.repository;

import com.YronJack.IronWars.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    Optional<Language> findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}
