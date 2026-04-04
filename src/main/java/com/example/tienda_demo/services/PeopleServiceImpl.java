package com.example.tienda_demo.services;

import com.example.tienda_demo.domain.Person;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service("world-people")
@ConditionalOnProperty(prefix = "implementation", value = "people", havingValue = "world-people")
public class PeopleServiceImpl implements PeopleService {
    ArrayList<Person> worldPeople = new ArrayList<>(
            List.of(new Person(1L, "Momo", "Morning"),
                    new Person(2L, "Rober", "Sina"),
                    new Person(3L, "Lago", "Coller"))
    );
    @Override
    public List<Person> listAllPeople(){
        return this.worldPeople;
    }
}
