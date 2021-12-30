package com.GameList.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "cedula")
        })
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    private String username;

    private String password;

    private String email;

    private String nombres;

    private String apellidos;

    private String cedula;

    private String celular;

    private Date fechaNacimiento;

    @Lob
    private String imagen;

    private String tokenrest;

    private Date fechaexpire;

    private Date sesiontime;

    private String edad;

    private Boolean estado;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Users() {
    }


    public Users(String username, String password, String email, String nombres, String apellidos, String cedula, String celular, Date fechaNacimiento, String imagen, String edad, Boolean estado) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.celular = celular;
        this.fechaNacimiento = fechaNacimiento;
        this.imagen = imagen;
        this.edad = edad;
        this.estado = estado;
    }

    public Users(String username, String email, String password, String email1, String nombres, String apellidos, String cedula, String celular, Date fechaNacimiento, String imagen, String edad, Boolean estado) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.celular = celular;
        this.fechaNacimiento = fechaNacimiento;
        this.imagen = imagen;
        this.edad = edad;
        this.estado = estado;
    }
}
