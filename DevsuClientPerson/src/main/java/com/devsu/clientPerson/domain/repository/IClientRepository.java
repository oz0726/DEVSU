package com.devsu.clientPerson.domain.repository;

import com.devsu.clientPerson.domain.entity.Client;
import com.devsu.clientPerson.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IClientRepository extends JpaRepository<Client, Integer> {
    public List<Client> findAll();
}
