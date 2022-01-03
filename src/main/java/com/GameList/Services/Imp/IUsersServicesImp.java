package com.GameList.Services.Imp;

import com.GameList.Repository.UsersRepository;
import com.GameList.Services.Interfaces.IUsersServices;
import com.GameList.Services.Transformer.UsersTransformer;
import com.GameList.Services.dto.UsersDTO;
import com.GameList.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IUsersServicesImp implements IUsersServices {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public Page<UsersDTO> read(Integer pageSize, Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        return usersRepository.findAll(pageable).map(UsersTransformer::getUsersDTOFromUsers);
    }

    @Override
    public Users updateProfile(UsersDTO usersDTO) {
       Users users = UsersTransformer.getUsersFromUsersDTO(usersDTO);

       return usersRepository.save(users);
    }

    @Override
    public void updateEstadoUser(Boolean estado, Integer id) {
        usersRepository.updateEstadoUser(estado, id);
    }

    @Override
    public void uploadImagenUser(String imagen, Integer id) {
        usersRepository.uploadImagenUser(imagen, id);
    }

    @Override
    public void delete(Integer id) {
        usersRepository.deleteById(id);
    }

    @Override
    public Integer countUsers() {
        return usersRepository.countUsers();
    }

    @Override
    public Integer countUsersInactive() {
        return usersRepository.countUsersInactive();
    }

    @Override
    public Integer countUsersActive() {
        return usersRepository.countUsersActive();
    }
}
