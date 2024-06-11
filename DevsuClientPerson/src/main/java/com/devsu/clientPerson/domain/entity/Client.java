package com.devsu.clientPerson.domain.entity;

import javax.persistence.*;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "CLIENT")
@Generated
public @Getter @Setter class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "STATE")
    private Boolean state;
    @Column(name = "PASSWORD")
    private String password;
    @JoinColumn(name = "PERSON", referencedColumnName = "ID", nullable = false)
    @OneToOne(optional = false, cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Person person;
}