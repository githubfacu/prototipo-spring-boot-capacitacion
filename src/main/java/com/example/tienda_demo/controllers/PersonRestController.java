package com.example.tienda_demo.controllers;

import com.example.tienda_demo.configurations.AppConfig;
import com.example.tienda_demo.domain.Person;
import com.example.tienda_demo.services.PeopleService;
import com.example.tienda_demo.validators.groups.OnCreate;
import com.example.tienda_demo.validators.groups.OnUpdate;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    public PersonRestController(@Lazy PeopleService peopleService){
        this.peopleService = peopleService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {

        if (id < 0){
            return ResponseEntity.badRequest().build();
        }
        for(Person person: peopleService.listAllPeople()){
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

        peopleService.savePerson(person);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(person.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Person> updatePerson(
            @RequestBody @Validated(OnUpdate.class) Person person) {

        Person updated = peopleService.updatePerson(person);

        if(updated == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id){

        if (id < 0){
            return ResponseEntity.badRequest().build();
        }

        try {
            peopleService.deletePerson(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }


}
