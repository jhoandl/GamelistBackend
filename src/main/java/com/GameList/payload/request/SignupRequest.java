package com.GameList.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
public class SignupRequest {

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

    private Set<String> role;

    private String edad;

    private Boolean estado;
}
