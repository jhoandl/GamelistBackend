package com.GameList.Services.Transformer;

import com.GameList.Services.dto.RoleDTO;
import com.GameList.models.Role;

public class RoleTransformer {

    public static RoleDTO getRoleDTOFromRole(Role role) {
        if (role == null) {
            return null;
        }

        RoleDTO dto = new RoleDTO();


        dto.setName(role.getName());

        return dto;
    }

    public static Role getRoleFromRoleDTO(RoleDTO dto) {
        if (dto == null) {
            return null;
        }

        Role role = new Role();


        role.setName(dto.getName());

        return role;
    }
}
