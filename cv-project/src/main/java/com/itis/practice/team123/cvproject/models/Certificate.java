package com.itis.practice.team123.cvproject.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "certificates")
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String yearOfReceipt;

    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "student_id")
    private Student student;
}
