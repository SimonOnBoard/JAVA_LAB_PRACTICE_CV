package com.itis.practice.team123.cvproject.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itis.practice.team123.cvproject.CvProjectApplicationTests;
import com.itis.practice.team123.cvproject.dto.UserDto;
import com.itis.practice.team123.cvproject.dto.UserForm;
import com.itis.practice.team123.cvproject.enums.Role;
import com.itis.practice.team123.cvproject.models.User;
import com.itis.practice.team123.cvproject.repositories.UsersRepository;
import com.itis.practice.team123.cvproject.repositories.WorksRepository;
import com.itis.practice.team123.cvproject.security.details.UserDetailsImpl;
import com.itis.practice.team123.cvproject.services.interfaces.AdminService;
import com.itis.practice.team123.cvproject.services.interfaces.WeightsAssigner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {

    @MockBean
    private AdminService adminService;

    @Autowired
    private ObjectMapper objectMapper;

    public AdminControllerTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Autowired
    private MockMvc mvc;

//    @WithUserDetails("admin")
//    @Test
//    void addUser() throws Exception {
//        UserForm userForm = new UserForm("Simon", "12345", "zzz@zz.ru", "TEACHER");
//        UserDto userDto = UserDto.builder().id(1L).name("Simon").email("zzz@zz.ru").role(Role.TEACHER).build();
//
//        mvc.perform(post("/addUser").contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(userForm))).andExpect(status().isOk());
//    }
//
//    private String asJsonString(Object object) throws JsonProcessingException {
//        return objectMapper.writeValueAsString(object);
//    }
}