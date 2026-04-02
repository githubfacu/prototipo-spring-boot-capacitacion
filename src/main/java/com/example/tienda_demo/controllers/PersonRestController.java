package com.example.tienda_demo.controllers;

import com.example.tienda_demo.domain.Person;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/people")
@Tag(name = "API people",
    description = "CRUD people Java spring boot practical"
)
public class PersonRestController {

    ArrayList<Person> people = new ArrayList<>(
            List.of(new Person(1L, "Lau", "Morning"),
                    new Person(2L, "Sil", "Robert"),
                    new Person(3L, "Loba", "Roja"))
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
        return ResponseEntity.ok(this.people);
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person){

        this.people.add(person);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(person.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity <Person> updatePerson(@RequestBody Person person) {

        for(Person per: this.people){
            if(per.getId().equals(person.getId())){
                per.setName(person.getName());
                per.setLastname(person.getLastname());
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
