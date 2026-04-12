package com.example.tienda_demo.services;

import com.example.tienda_demo.domain.Person;
import com.example.tienda_demo.persistance.entities.PersonEntity;
import com.example.tienda_demo.persistance.repositories.PersonRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("default")
@ConditionalOnProperty(prefix = "implementation", value = "people", havingValue = "default")
public class PeopleServiceDefaultImpl implements PeopleService {

    PersonRepository personRepository;

    public PeopleServiceDefaultImpl(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> listAllPeople(){
        return this.personRepository.findAll()
                .stream()
                .map(personEntity -> new Person(
                        personEntity.getId(),
                        personEntity.getName(),
                        personEntity.getLastname(),
                        personEntity.getEmail(),
                        personEntity.getPassword()
                )).collect(Collectors.toList());
    }

    @Override
    public Person savePerson(Person person) {
        PersonEntity entity =
                new PersonEntity(
                        null,
                        person.getName(),
                        person.getLastname(),
                        person.getEmail(),
                        person.getPassword()
                );

        PersonEntity saved =
                personRepository.save(entity);

        return new Person(
                saved.getId(),
                saved.getName(),
                saved.getLastname(),
                saved.getEmail(),
                saved.getPassword()
        );
    }

    @Override
    public Person updatePerson(Person person) {

        var entityOpt = personRepository.findById(person.getId());

        if(entityOpt.isEmpty()){
            return null;
        }

        var entity = entityOpt.get();

        entity.setName(person.getName());
        entity.setLastname(person.getLastname());
        entity.setPassword(person.getPassword());

        var updated = personRepository.save(entity);

        return new Person(
                updated.getId(),
                updated.getName(),
                updated.getLastname(),
                updated.getEmail(),
                updated.getPassword()
        );
    }

    @Override
    public void deletePerson(Long id) {

        if(!personRepository.existsById(id)){
            throw new RuntimeException("Person not found");
        }

        personRepository.deleteById(id);
    }
}
