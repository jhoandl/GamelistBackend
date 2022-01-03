package com.GameList.Services.Interfaces;

import com.GameList.Services.dto.UsersDTO;
import com.GameList.models.Users;
import org.springframework.data.domain.Page;

public interface IUsersServices {

    public Page<UsersDTO> read(Integer pageSize, Integer pageNumber);

    public Users updateProfile (UsersDTO usersDTO);

    void updateEstadoUser(Boolean estado, Integer id);

    void uploadImagenUser(String imagen, Integer id);

    void delete(Integer id);

    Integer countUsers();

    Integer countUsersInactive();

    Integer countUsersActive();


}
