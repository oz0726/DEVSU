package com.devsu.clientPerson.domain.repository;

import com.devsu.clientPerson.domain.entity.Client;
import com.devsu.clientPerson.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IClientRepository extends JpaRepository<Client, Integer> {
    @Query("SELECT c FROM Client c WHERE c.person.id = :id")
    public List<Client> findByPersonId(@Param("id") Integer id);
}
