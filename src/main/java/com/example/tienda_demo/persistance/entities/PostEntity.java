package com.example.tienda_demo.persistance.entities;

import com.example.tienda_demo.domain.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NonNull
    private String message;
    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private PersonEntity person;
}
