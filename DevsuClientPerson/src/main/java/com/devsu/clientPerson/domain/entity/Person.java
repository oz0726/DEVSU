package com.devsu.clientPerson.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PERSON")
@Generated
public @Getter @Setter class Person {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "GENRE")
    private String genre;
    @Column(name = "AGE")
    private String age;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
}