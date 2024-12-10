package com.cursoudemy.PostGresConnectionSpring.services;

import com.cursoudemy.PostGresConnectionSpring.excpetions.ResourceNotFoundException;
import com.cursoudemy.PostGresConnectionSpring.model.Person;
import com.cursoudemy.PostGresConnectionSpring.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;

    public List<Person> findAll() {

        logger.info("finding all");

        return personRepository.findAll();
    }


    public Person findById(Long id) {

        logger.info("finding by id");

        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Record Found for this id"));
    }

    public Person create(Person person) {
        logger.info("Creating a person");
        return personRepository.save(person);
    }

    public Person update(Person person) {
        logger.info("Update new person");
        Person person1 = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No Record found for this id"));

        person1.setAddress(person.getAddress());
        person1.setFirstName(person.getFirstName());
        person1.setLastName(person.getLastName());
        person1.setGender(person.getGender());
        return personRepository.save(person1);

    }

    public void delete(Long id) {
        logger.info("Delete Person");

        Person person1 = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Record found for this id"));

        personRepository.delete(person1);
    }


}
