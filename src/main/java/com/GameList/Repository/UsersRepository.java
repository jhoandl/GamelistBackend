package com.GameList.Repository;

import com.GameList.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByCedula(String cedula);

    Boolean existsByUsername(String username);

    @Query(value = "update users set estado = ? where id = ?", nativeQuery = true)
    void updateEstadoUser(Boolean estado, Integer id);

    @Query(value = "update users set imagen = ? where id = ?", nativeQuery = true)
    void uploadImagenUser(String imagen, Integer id);

    @Query(value = "select count(*) from users", nativeQuery = true)
    Integer countUsers();

    @Query(value = "select count(*) from users where estado = false", nativeQuery = true)
    Integer countUsersInactive();

    @Query(value = "select count(*) from users where estado = true", nativeQuery = true)
    Integer countUsersActive();

}
