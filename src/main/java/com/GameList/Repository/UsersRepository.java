package com.GameList.Repository;

import com.GameList.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByCedula(String cedula);

    Boolean existsByUsername(String username);
}
