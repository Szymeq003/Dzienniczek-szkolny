package com.example.demo.repository;

import com.example.demo.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findByStudentId(Long studentId);
    List<Grade> findBySubjectId(Long subjectId);
    List<Grade> findByStudentIdAndSubjectId(Long studentId, Long subjectId);

    @Query("SELECT AVG(CASE " +
           "WHEN g.value = '1' THEN 1.0 " +
           "WHEN g.value = '1+' THEN 1.5 " +
           "WHEN g.value = '2-' THEN 1.75 " +
           "WHEN g.value = '2' THEN 2.0 " +
           "WHEN g.value = '2+' THEN 2.5 " +
           "WHEN g.value = '3-' THEN 2.75 " +
           "WHEN g.value = '3' THEN 3.0 " +
           "WHEN g.value = '3+' THEN 3.5 " +
           "WHEN g.value = '4-' THEN 3.75 " +
           "WHEN g.value = '4' THEN 4.0 " +
           "WHEN g.value = '4+' THEN 4.5 " +
           "WHEN g.value = '5-' THEN 4.75 " +
           "WHEN g.value = '5' THEN 5.0 " +
           "WHEN g.value = '5+' THEN 5.5 " +
           "WHEN g.value = '6-' THEN 5.75 " +
           "WHEN g.value = '6' THEN 6.0 " +
           "ELSE 0.0 END) FROM Grade g WHERE g.student.id = :studentId")
    Double getAverageGradeByStudentId(@Param("studentId") Long studentId);
}
