package com.itis.practice.team123.cvproject.services.interfaces;

import com.itis.practice.team123.cvproject.models.User;
import org.springframework.ui.Model;

public interface ProfileService {
    String getProfile(User user, Model model) throws IllegalArgumentException;

    String getProfile(Long id, Model model) throws IllegalArgumentException;

    Object getProfileForApi(User user);

    Object getProfileForApi(Long id);
}
