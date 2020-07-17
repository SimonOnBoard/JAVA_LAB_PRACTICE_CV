package com.itis.practice.team123.cvproject.services.impl;

import com.itis.practice.team123.cvproject.dto.CompanyEditForm;
import com.itis.practice.team123.cvproject.enums.Role;
import com.itis.practice.team123.cvproject.models.Company;
import com.itis.practice.team123.cvproject.models.Post;
import com.itis.practice.team123.cvproject.repositories.CompanyRepository;
import com.itis.practice.team123.cvproject.repositories.PostRepository;
import com.itis.practice.team123.cvproject.services.interfaces.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CompanyServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private PostRepository postRepository;

    private CompanyService companyService;

    public CompanyServiceImplTest() {
        MockitoAnnotations.initMocks(this);
        this.companyService = new CompanyServiceImpl(companyRepository, postRepository);
    }

    @Nested
    class CompanyAddPostTests {
        private Company company;
        private Post post;
        private ArgumentCaptor<Post> postArgumentCaptor;
        private ArgumentCaptor<Company> companyArgumentCaptor;

        @BeforeEach
        public void init() {
            company = new Company(1L, "12345", "11111", Role.COMPANY, "company@gmail.com");
            company.setPosts(new ArrayList<>());
            post = Post.builder().name("1").description("1").build();
            postArgumentCaptor = ArgumentCaptor.forClass(Post.class);
            companyArgumentCaptor = ArgumentCaptor.forClass(Company.class);
        }

        @Test
        void addPostException() {
            given(postRepository.save(any(Post.class))).willThrow(RuntimeException.class);
            assertThrows(RuntimeException.class, () -> companyService.addPost(company, post));
            verify(postRepository).save(postArgumentCaptor.capture());
            assertThat(postArgumentCaptor.getValue()).isEqualToComparingFieldByField(post);
        }
    }

    @Nested
    class GetCompanyTests {
        private Company company;
        private ArgumentCaptor<Long> idCaptor;

        @BeforeEach
        public void init() {
            company = new Company(1L, "12345", "11111", Role.COMPANY, "company@gmail.com");
            company.setPosts(new ArrayList<>());
            idCaptor = ArgumentCaptor.forClass(Long.class);
        }

        @Test
        void getCompanyException() {
            given(companyRepository.findById(anyLong())).willThrow(IllegalArgumentException.class);
            assertThrows(IllegalArgumentException.class, () -> companyService.getCompany(1L));
            verify(companyRepository).findById(idCaptor.capture());
            assertThat(idCaptor.getValue()).isEqualTo(1L);
        }

        @Test
        void getCompanySuccess() {
            given(companyRepository.findById(anyLong())).willReturn(Optional.ofNullable(company));
            Company company = companyService.getCompany(1L);
            assertThat(company).isEqualToComparingFieldByField(company);
        }
    }

    @Nested
    class RemovePostTests {
        private Company company;
        private Post post;
        private ArgumentCaptor<Long> idCaptor;

        @BeforeEach
        public void init() {
            company = new Company(1L, "12345", "11111", Role.COMPANY, "company@gmail.com");
            company.setPosts(new ArrayList<>());
            post = Post.builder().name("1").description("1").build();
            company.getPosts().add(post);
            idCaptor = ArgumentCaptor.forClass(Long.class);
        }


        @Test
        void removePostException() {
            given(postRepository.getById(anyLong())).willThrow(RuntimeException.class);
            assertThrows(RuntimeException.class, () -> companyService.removePost(company, 1L));
            verify(postRepository).getById(idCaptor.capture());
            assertThat(idCaptor.getValue()).isEqualTo(1L);
        }

        @Test
        void removePostSuccess() {
            given(postRepository.getById(anyLong())).willReturn(post);
            assertThat(company.getPosts().size()).isEqualTo(1);
            companyService.removePost(company, 1L);
            verify(postRepository, times(1)).delete(post);
            verify(postRepository).getById(idCaptor.capture());
            assertThat(idCaptor.getValue()).isEqualTo(1L);
            assertThat(company.getPosts().size()).isEqualTo(0);
        }
    }


    @Nested
    class UpdateCompanyService {
        private Company company;
        private CompanyEditForm companyEditForm;
        private Post post;
        private ArgumentCaptor<Long> idCaptor;
        private ArgumentCaptor<Company> companyArgumentCaptor;
        @BeforeEach
        public void init() {
            company = new Company(1L, "12345", "11111", Role.COMPANY, "company@gmail.com");
            company.setPosts(new ArrayList<>());
            post = Post.builder().name("1").description("1").build();
            company.getPosts().add(post);
            idCaptor = ArgumentCaptor.forClass(Long.class);
            companyEditForm = CompanyEditForm.builder()
                    .address("123")
                    .description("Super description")
                    .phone("89083397797")
                    .name("new Vayland company").build();
            companyArgumentCaptor = ArgumentCaptor.forClass(Company.class);
        }


        @Test
        void updateCompanyByFormAndIdFail() {
            given(companyRepository.findById(anyLong())).willReturn(Optional.empty());
            assertThrows(IllegalArgumentException.class,
                    () -> companyService.updateCompany(companyEditForm, 1L));
            verify(companyRepository).findById(idCaptor.capture());
            assertThat(1L).isEqualTo(idCaptor.getValue());
            verify(companyRepository, times(1)).findById(anyLong());
        }

        @Test
        void updateCompanyByFormAndTeacherFail() {
            given(companyRepository.saveAndFlush(anyObject())).willThrow(RuntimeException.class);
            assertThrows(RuntimeException.class, () -> companyService.updateCompany(companyEditForm, company));
            checkCompanyInfo();
        }

        private void checkCompanyInfo() {
            verify(companyRepository).saveAndFlush(companyArgumentCaptor.capture());
            Company capturedCompany = companyArgumentCaptor.getValue();
            verify(companyRepository,times(1)).saveAndFlush(any(Company.class));
            assertThat(capturedCompany.getName()).isEqualTo(companyEditForm.getName());
            assertThat(capturedCompany.getDescription()).isEqualTo(companyEditForm.getDescription());
            assertThat(capturedCompany.getAddress()).isEqualTo(companyEditForm.getAddress());
            assertThat(capturedCompany.getPhone()).isEqualTo(companyEditForm.getPhone());
        }

        @Test
        void updateCompanyByFormAndIdSuccess() {
            given(companyRepository.findById(anyLong())).willReturn(Optional.of(company));
            companyService.updateCompany(companyEditForm, 1L);
            verify(companyRepository).findById(idCaptor.capture());
            assertThat(1L).isEqualTo(idCaptor.getValue());
            checkCompanyInfo();
        }

        @Test
        void updateCompanyByFormAndTeacherSuccess() {
            companyService.updateCompany(companyEditForm, company);
            checkCompanyInfo();
        }
    }
}