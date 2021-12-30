package com.GameList.Repository;

import com.GameList.models.Enumeration.ERole;
import com.GameList.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
