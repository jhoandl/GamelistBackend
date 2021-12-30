package com.GameList.Services.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import java.util.Date;

@Setter
@Getter
public class UsersDTO {

    private int id;

    private String username;

    private String password;

    private String email;

    private String nombres;

    private String apellidos;

    private String cedula;

    private String celular;

    private Date fechaNacimiento;

    private String imagen;

    private String edad;

    private Boolean estado;

}
