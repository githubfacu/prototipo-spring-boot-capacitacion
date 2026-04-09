package com.example.tienda_demo.controllers;

import com.example.tienda_demo.configurations.AppConfig;
import com.example.tienda_demo.configurations.StoreParametersConfig;
import com.example.tienda_demo.domain.Person;
import com.example.tienda_demo.services.PeopleService;
import com.example.tienda_demo.validators.groups.OnCreate;
import com.example.tienda_demo.validators.groups.OnUpdate;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/people")
@Tag(name = "API people",
    description = "CRUD people Java spring boot practical"
)
public class PersonRestController {

    PeopleService peopleService;
    AppConfig appConfig;
    StoreParametersConfig storeConfig;

    public PersonRestController(@Lazy PeopleService peopleService, AppConfig appConfig, StoreParametersConfig storeConfig){
        log.info("AppConfig: {}", appConfig);
        log.info("StoreConfig: {}", storeConfig);
        this.peopleService = peopleService;
    }

    ArrayList<Person> people = new ArrayList<>(
            List.of(new Person(1L, "Col", "Dano", "dano@email.com", "123ABC"),
                    new Person(2L, "Ras", "Lock", "lock@email.com", "123aaa"),
                    new Person(3L, "Hi", "Den", "den@email.com", "123bbb"))
    );

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {

        if (id < 0){
            return ResponseEntity.badRequest().build();
        }
        for(Person person: this.people){
            if(person.getId().equals(id)){
                return ResponseEntity.ok(person);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Person>> listPerson(){
        List<Person> people = peopleService.listAllPeople();
        return ResponseEntity.ok(people);
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody @Validated(OnCreate.class) Person person){

        this.people.add(person);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(person.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity <Person> updatePerson(@RequestBody @Validated(OnUpdate.class) Person person) {

        for(Person per: this.people){
            if(per.getId().equals(person.getId())){
                per.setName(person.getName());
                per.setLastname(person.getLastname());
                per.setPassword(person.getPassword());
                return ResponseEntity.ok(person);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePerson(@PathVariable Long id){
        if (id < 0){
            return ResponseEntity.badRequest().build();
        }
        for(Person person: this.people){
            if(person.getId().equals(id)){
                this.people.remove(person);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }


}
