package com.itis.practice.team123.cvproject.repositories;

import com.itis.practice.team123.cvproject.models.Student;
import com.itis.practice.team123.cvproject.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface StudentsRepository extends JpaRepository<Student, Long> {
    @Query(value = "select c.student_id from competences c where c.tag_id = :tag_id", nativeQuery = true)
    List<Long> findByTag(@Param("tag_id") Long tag_id);
}
