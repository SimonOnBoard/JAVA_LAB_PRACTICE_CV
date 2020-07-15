package com.itis.practice.team123.cvproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "certificates")
public class Certificate {
    @Override
    public String toString() {
        return "Certificate{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", yearOfReceipt='" + yearOfReceipt + '\'' +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String yearOfReceipt;

    @JsonIgnore
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    @JoinColumn(name = "student_id")
    private Student student;
}
