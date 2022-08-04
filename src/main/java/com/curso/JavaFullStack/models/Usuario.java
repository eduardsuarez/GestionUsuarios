package com.curso.JavaFullStack.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Column(name = "id")
    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre")
    @Getter @Setter
    private String nombre;

    @Column(name = "apellido")
    @Getter @Setter
    private String apellido;

    @Column(name = "email")
    @Getter @Setter
    private String email;

    @Column(name = "telefono")
    @Getter @Setter
    private String telefono;

    @Column(name = "password")
    @Getter @Setter
    private String password;


}
