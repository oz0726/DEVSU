package com.devsu.clientPerson.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "CLIENT")
@NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c")
@Generated
public @Getter @Setter class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "STATE")
    private Integer state;
    @Column(name = "PASSWORD")
    private String password;
    @JoinColumn(name = "PERSON", referencedColumnName = "ID", nullable = false)
    @OneToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Person person;
}