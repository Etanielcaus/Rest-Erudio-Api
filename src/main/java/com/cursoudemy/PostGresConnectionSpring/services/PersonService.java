package com.cursoudemy.PostGresConnectionSpring.services;

import com.cursoudemy.PostGresConnectionSpring.data.v2.PersonVOV2;
import com.cursoudemy.PostGresConnectionSpring.excpetions.ResourceNotFoundException;
import com.cursoudemy.PostGresConnectionSpring.mapper.ModelMapperCustom;
import com.cursoudemy.PostGresConnectionSpring.mapper.ModelMapperDTO;
import com.cursoudemy.PostGresConnectionSpring.model.Person;
import com.cursoudemy.PostGresConnectionSpring.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    ModelMapperCustom mapperCustom;

    @Autowired
    PersonRepository personRepository;

    public List<PersonVOV2> findAll() {

        logger.info("finding all");

        return ModelMapperDTO.parseListObjects(personRepository.findAll(), PersonVOV2.class);
    }


    public PersonVOV2 findById(Long key) {

        logger.info("finding by id");

        Person person = personRepository.findById(key)
                .orElseThrow(() -> new ResourceNotFoundException("No Record Found for this key"));

        return ModelMapperDTO.parseObject(person, PersonVOV2.class);

    }

    public PersonVOV2 create(PersonVOV2 person) {
        logger.info("Creating a person");

        Person parseObject = ModelMapperDTO.parseObject(person, Person.class);
        PersonVOV2 personVOV2 = ModelMapperDTO.parseObject(personRepository.save(parseObject), PersonVOV2.class);

        return personVOV2;
    }

    public PersonVOV2 createv2(PersonVOV2 person) {
        logger.info("Creating a person with V2");

        Person personDomain = mapperCustom.convertVoToEntity(person);
        PersonVOV2 personVO2 = mapperCustom.convertEntityToVO(personRepository.save(personDomain));

        return personVO2;
    }

    public PersonVOV2 update(PersonVOV2 person) {
        logger.info("Update new person");
        Person person1 = personRepository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No Record found for this key"));

        person1.setAddress(person.getAddress());
        person1.setFirstName(person.getFirstName());
        person1.setLastName(person.getLastName());
        person1.setGender(person.getGender());

        PersonVOV2 personVOV2 = ModelMapperDTO.parseObject(personRepository.save(person1), PersonVOV2.class);

        return personVOV2;
    }

    public void delete(Long key) {
        logger.info("Delete Person");

        Person person1 = personRepository.findById(key)
                .orElseThrow(() -> new ResourceNotFoundException("No Record found for this key"));
        personRepository.delete(person1);
    }

    public ResponseEntity<PersonVOV2> findByFirstName(String firstName) {
        logger.info("Finding by first name");

        Person person = personRepository.findByFirstName(firstName);

        PersonVOV2 personVOV2 = ModelMapperDTO.parseObject(person, PersonVOV2.class);

        return ResponseEntity.ok(personVOV2);
    }

}
