package com.devsu.clientPerson.domain.repository;

import com.devsu.clientPerson.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * JPA Connection Repository
 *
 * @author Olman Ibanez
 */
public interface IPersonRepository extends JpaRepository<Person, Integer> {

}
