package com.GameList.Services.Transformer;

import com.GameList.Services.dto.UsersDTO;
import com.GameList.models.Users;

public class UsersTransformer {

    public static UsersDTO getUsersDTOFromUsers(Users users) {
        if (users == null) {
            return  null;
        }

        UsersDTO dto = new UsersDTO();

        dto.setUsername(users.getUsername());
        dto.setPassword(users.getPassword());
        dto.setEmail(users.getEmail());
        dto.setNombres(users.getNombres());
        dto.setApellidos(users.getApellidos());
        dto.setCedula(users.getCedula());
        dto.setCelular(users.getCelular());
        dto.setFechaNacimiento(users.getFechaNacimiento());
        dto.setImagen(users.getImagen());
        dto.setEdad(users.getEdad());
        dto.setEstado(users.getEstado());

        return dto;
    }

    public static Users getUsersFromUsersDTO(UsersDTO dto) {
        if (dto == null) {
            return  null;
        }

        Users users = new Users();

        users.setUsername(dto.getUsername());
        users.setPassword(dto.getPassword());
        users.setEmail(dto.getEmail());
        users.setNombres(dto.getNombres());
        users.setApellidos(dto.getApellidos());
        users.setCedula(dto.getCedula());
        users.setCelular(dto.getCelular());
        users.setFechaNacimiento(dto.getFechaNacimiento());
        users.setImagen(dto.getImagen());
        users.setEdad(dto.getEdad());
        users.setEstado(dto.getEstado());

        return users;
    }
}
