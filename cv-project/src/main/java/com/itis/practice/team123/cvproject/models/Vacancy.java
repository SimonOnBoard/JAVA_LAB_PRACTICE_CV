package com.itis.practice.team123.cvproject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vacancy_title")
    private String title;

    @Column(name = "vacancy_salary_min")
    private Integer salaryMin;

    @Column(name = "vacancy_salary_max")
    private Integer salaryMax;

    @Column(name = "vacancy_description")
    private String description;

    @ElementCollection
    @CollectionTable(name = "vacancy_addresses",
            joinColumns = @JoinColumn(name = "vacancy_id")
    )
    @Column(name = "address")
    private Set<String> addresses;

    @ElementCollection
    @CollectionTable(name = "vacancy_phone_numbers",
            joinColumns = @JoinColumn(name = "vacancy_id")
    )
    @Column(name = "phone_number")
    private Set<String> phoneNumbers;

    @ElementCollection
    @CollectionTable(name = "vacancy_emails",
            joinColumns = @JoinColumn(name = "vacancy_id")
    )
    @Column(name = "email")
    private Set<String> emails;

    @ManyToOne
    private Company company;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "vacancies_tags",
            joinColumns = @JoinColumn(name = "vacancy_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

}
