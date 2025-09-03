package com.YronJack.IronWars.repository;

import com.YronJack.IronWars.model.Exam;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    List<Exam> findExamByStudent_Id(Long studentId);


}
