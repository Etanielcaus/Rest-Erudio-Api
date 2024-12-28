package com.cursoudemy.PostGresConnectionSpring.controller;

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
    public List<PersonVOV2> findAll() {
        return personService.findAll();
    }

    @GetMapping(
            value = "/v2/findAll",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<PersonVOV2> findAllv2() {
        return personService.findAll();
    }

    @GetMapping(
            value = "/v2/findAllYaml",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML})
    public List<PersonVOV2> findAllYaml() {
        return personService.findAll();
    }

    @GetMapping(
            value = "/v2/{id}",
            produces = MediaType.APPLICATION_JSON)
    public PersonVOV2 findById(@PathVariable(value = "id") Long id) {
        return personService.findById(id);
    }

    @PostMapping(
            value = "/v2",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public PersonVOV2 create(@RequestBody PersonVOV2 person) {
        return personService.create(person);
    }

    @PutMapping(
            value = "/v2",
            consumes = MediaType.APPLICATION_JSON,
            produces = MediaType.APPLICATION_JSON)
    public PersonVOV2 update(@RequestBody PersonVOV2 person) {
        return personService.update(person);
    }

    @DeleteMapping(value = "/v2/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/v2/findByFirstName/{firstName}",
    produces = MediaType.APPLICATION_JSON)
    public ResponseEntity<PersonVOV2> findByFirstName(@PathVariable(value = "firstName") String firstName) {
        return personService.findByFirstName(firstName);
    }
}
