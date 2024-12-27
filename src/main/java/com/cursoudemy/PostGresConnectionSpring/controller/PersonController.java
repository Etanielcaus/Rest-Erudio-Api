package com.cursoudemy.PostGresConnectionSpring.controller;

import com.cursoudemy.PostGresConnectionSpring.data.v1.PersonVO;
import com.cursoudemy.PostGresConnectionSpring.data.v2.PersonVOV2;
import com.cursoudemy.PostGresConnectionSpring.services.PersonService;
import com.cursoudemy.PostGresConnectionSpring.util.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    

    @GetMapping(
            produces = MediaType.APPLICATION_JSON)
    public List<PersonVO> findAll() {
        return personService.findAll();
    }

    @GetMapping(
            value = "/v2/findAll",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<PersonVO> findAllv2() {
        return personService.findAll();
    }

    @GetMapping(value = "/findAllYaml",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
    public List<PersonVO> findAllYaml() {
        return personService.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON)
    public PersonVO findById(@PathVariable(value = "id") Long id) {
        return personService.findById(id);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public PersonVO create(@RequestBody PersonVO person) {
        return personService.create(person);
    }

    @PostMapping(value = "/v2",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public PersonVOV2 createv2(@RequestBody PersonVOV2 person) {
        return personService.createv2(person);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public PersonVO update(@RequestBody PersonVO person) {
        return personService.update(person);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/findByFirstName/{firstName}",
    produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<PersonVO> findByFirstName(@PathVariable(value = "firstName") String firstName) {
        return personService.findByFirstName(firstName);
    }
}
