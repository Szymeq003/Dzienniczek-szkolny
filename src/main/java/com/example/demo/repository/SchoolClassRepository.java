package com.example.demo.repository;

import com.example.demo.model.SchoolClass;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
    
    @Override
    @EntityGraph(attributePaths = {"teacher"})
    List<SchoolClass> findAll();

    Optional<SchoolClass> findByName(String name);
}
