package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.models.Tag;
import com.itis.practice.team123.cvproject.repositories.TagsRepository;
import com.itis.practice.team123.cvproject.services.interfaces.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagsRepository tagsRepository;
    @Override
    public List<Tag> getAllTags() {
        return tagsRepository.findAll();
    }
}
