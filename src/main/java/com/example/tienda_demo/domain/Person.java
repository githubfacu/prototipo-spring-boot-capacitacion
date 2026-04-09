package com.example.tienda_demo.domain;

import com.example.tienda_demo.validators.Password;
import com.example.tienda_demo.validators.groups.OnCreate;
import com.example.tienda_demo.validators.groups.OnUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Person {

    private Long id;
    private String name;
    @NotBlank
    private String lastname;
    @Email(groups = OnCreate.class)
    private String email;
    @Password(groups = { OnCreate.class, OnUpdate.class })
    private String password;

    public Person(Long id, String name, String lastname, String email, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
