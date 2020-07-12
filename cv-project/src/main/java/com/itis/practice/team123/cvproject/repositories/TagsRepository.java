package com.itis.practice.team123.cvproject.repositories;

import com.itis.practice.team123.cvproject.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface TagsRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
    List<Tag> findAllByNameIn(List<String> names);
}
