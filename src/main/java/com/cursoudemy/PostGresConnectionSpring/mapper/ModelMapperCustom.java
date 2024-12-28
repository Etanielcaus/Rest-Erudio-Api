package com.cursoudemy.PostGresConnectionSpring.mapper;

import com.cursoudemy.PostGresConnectionSpring.data.v2.PersonVOV2;
import com.cursoudemy.PostGresConnectionSpring.model.Person;
import org.springframework.stereotype.Service;

@Service
public class ModelMapperCustom {

    public PersonVOV2 convertEntityToVO(Person person){
        PersonVOV2 person1 = new PersonVOV2();
        person1.setKey(person.getId());
        person1.setAddress(person.getAddress());
        person1.setGender(person.getGender());
        person1.setFirstName(person.getFirstName());
        person1.setLastName(person.getLastName());
        return person1;
    }

    public Person convertVoToEntity(PersonVOV2 personVOV2){
        Person person1 = new Person();
        person1.setId(personVOV2.getKey());
        person1.setAddress(personVOV2.getAddress());
        person1.setGender(personVOV2.getGender());
        person1.setFirstName(personVOV2.getFirstName());
        person1.setLastName(personVOV2.getLastName());

        return person1;
    }
}
