package com.example.tienda_demo.persistance.entities;

import com.example.tienda_demo.validators.Password;
import com.example.tienda_demo.validators.groups.OnCreate;
import com.example.tienda_demo.validators.groups.OnUpdate;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Set;

@Entity
@Table(name = "person")
@Data
@NoArgsConstructor
public class PersonEntity {
    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NonNull
    private String name;
    @NotBlank
    private String lastname;
    @Email(groups = OnCreate.class)
    private String email;
    @Password(groups = { OnCreate.class, OnUpdate.class })
    private String password;
    @OneToMany(mappedBy = "person")
    private Set<PostEntity> posts;

    public PersonEntity(Long id, String name, String lastname, String email, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

}
