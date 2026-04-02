package com.example.tienda_demo.services;

import com.example.tienda_demo.domain.Person;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("indian")
public class PeopleServiceIndianImpl implements PeopleService{

    ArrayList<Person> people = new ArrayList<>(
            List.of(new Person(1L, "Raj", "Krishna"),
                    new Person(2L, "Bahubali", "Kumar"),
                    new Person(3L, "Radha", "Kumar"))
    );

    public List<Person> listAllPeople(){
        return this.people;
    }
}
