package com.devsu.clientPerson.domain.repository;

import com.devsu.clientPerson.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<Person, Integer> {

}