package com.example.tienda_demo.services;

import com.example.tienda_demo.domain.Person;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface PeopleService {

    public List<Person> listAllPeople();
    public Person savePerson(Person person);
    public Person updatePerson(Person person);
    public void deletePerson(Long id);
}
