package com.cursoudemy.PostGresConnectionSpring.repositories;

import com.cursoudemy.PostGresConnectionSpring.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
