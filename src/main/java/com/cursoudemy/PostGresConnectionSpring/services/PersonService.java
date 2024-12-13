package com.cursoudemy.PostGresConnectionSpring.services;

import com.cursoudemy.PostGresConnectionSpring.data.PersonVO;
import com.cursoudemy.PostGresConnectionSpring.excpetions.ResourceNotFoundException;
import com.cursoudemy.PostGresConnectionSpring.mapper.ModelMapperDTO;
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

    public List<PersonVO> findAll() {

        logger.info("finding all");

        return ModelMapperDTO.parseListObjects(personRepository.findAll(), PersonVO.class);
    }


    public PersonVO findById(Long id) {

        logger.info("finding by id");

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Record Found for this id"));

        return ModelMapperDTO.parseObject(person, PersonVO.class);

    }

    public PersonVO create(PersonVO person) {
        logger.info("Creating a person");

        Person parseObject = ModelMapperDTO.parseObject(person, Person.class);
        PersonVO personVO = ModelMapperDTO.parseObject(personRepository.save(parseObject), PersonVO.class);

        return personVO;
    }

    public PersonVO update(PersonVO person) {
        logger.info("Update new person");
        Person person1 = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No Record found for this id"));

        person1.setAddress(person.getAddress());
        person1.setFirstName(person.getFirstName());
        person1.setLastName(person.getLastName());
        person1.setGender(person.getGender());

        PersonVO personVO = ModelMapperDTO.parseObject(personRepository.save(person1), PersonVO.class);

        return personVO;
    }

    public void delete(Long id) {
        logger.info("Delete Person");

        Person person1 = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Record found for this id"));

        personRepository.delete(person1);
    }


}
