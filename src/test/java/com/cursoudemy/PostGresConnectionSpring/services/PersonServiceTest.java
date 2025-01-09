package com.cursoudemy.PostGresConnectionSpring.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cursoudemy.PostGresConnectionSpring.data.v2.PersonVOV2;
import com.cursoudemy.PostGresConnectionSpring.model.Person;
import com.cursoudemy.PostGresConnectionSpring.repositories.PersonRepository;
import com.cursoudemy.PostGresConnectionSpring.testUnit.MockPerson;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;

    @BeforeEach
    void setUpMocks() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAll() {
        Mockito.when(repository.findAll()).thenReturn(input.mockEntityList());
        var result = service.findAll();
        assertNotNull(result);
        assertEquals(14, result.size());    
        assertEquals(14, result.size());
        assertEquals(14, result.size());
        assertEquals(14, result.size());    
    }

    @Test
    @DisplayName("Find a person by id with success")
    void findById() {
        Person person = input.mockEntity();
        person.setId(1L);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(person));


        var result = service.findById(1L);
        
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertEquals(1L, result.getKey());
        assertEquals("First Name Test0", result.getFirstName());
        assertEquals("Last Name Test0", result.getLastName());
        assertEquals("Addres Test0", result.getAddress());
        assertEquals("Male", result.getGender());
    }

    @Test
    @DisplayName("Create a person with success")
    void create() {
        Person person = input.mockEntity();

        Person persisted = person;
        persisted.setId(1L);

        PersonVOV2 vo = input.mockVOV2();

        vo.setKey(1L); 
        Mockito.when(repository.save(person)).thenReturn(persisted);

        var result = service.create(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertEquals(1L, result.getKey());
        assertEquals("First Name Test0", result.getFirstName());
        assertEquals("Last Name Test0", result.getLastName());
        assertEquals("Addres Test0", result.getAddress());
        assertEquals("Male", result.getGender());

    }

    @Test
    @DisplayName("Update a person with success")
    void update() {
        Person person = input.mockEntity();
        person.setId(1L);

        Person persisted = person;
        persisted.setId(1L);

        PersonVOV2 vo = input.mockVOV2();
        vo.setKey(1L);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(person));
        Mockito.when(repository.save(person)).thenReturn(persisted);

        var result = service.update(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
    }

    @Test
    @DisplayName("Delete a person with success")
    void delete() {
        Person person = input.mockEntity();
        person.setId(1L);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(person));
        Mockito.doNothing().when(repository).delete(person);

        service.delete(1L);
    }

    @SuppressWarnings("null")
    @Test
    @DisplayName("Find a person by first name with success")
    void findByFirstName() {
        Person person = input.mockEntity();
        person.setId(1L);

        Mockito.when(repository.findByFirstName(person.getFirstName())).thenReturn(person);

        var result = service.findByFirstName(person.getFirstName());

        assertNotNull(result);
        assertNotNull(result.getBody());
        assertEquals(1L, result.getBody().getKey());
        assertEquals("First Name Test0", result.getBody().getFirstName());
        assertEquals("Last Name Test0", result.getBody().getLastName());
        assertEquals("Addres Test0", result.getBody().getAddress());
        assertEquals("Male", result.getBody().getGender());
    }
}