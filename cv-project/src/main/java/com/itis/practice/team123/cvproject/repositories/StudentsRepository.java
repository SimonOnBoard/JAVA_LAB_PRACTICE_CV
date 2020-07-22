package com.itis.practice.team123.cvproject.repositories;


import com.itis.practice.team123.cvproject.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface StudentsRepository extends JpaRepository<Student, Long> {
    @Query(value = "select s from Student s, Competence c where (c.tag.id = :tag_id) and (c.student.id = s.id)")
    List<Student> findByTag(@Param("tag_id") Long tagId);

}
