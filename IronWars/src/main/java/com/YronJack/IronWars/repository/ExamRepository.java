package com.YronJack.IronWars.repository;

import com.YronJack.IronWars.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Long>{
}
