package com.GameList.Web.res;

import com.GameList.Services.Interfaces.IUsersServices;
import com.GameList.Services.dto.UsersDTO;
import com.GameList.models.Users;
import com.GameList.payload.request.EstadoRequest;
import com.GameList.payload.request.UploadImagenRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UsersResource {

    @Autowired
    IUsersServices iUsersServices;

    @GetMapping("/get-users")
    public Page<UsersDTO> read(@RequestParam(value = "pageSize") Integer pageSize, @RequestParam(value = "pageNumber") Integer pageNumber) {
        return iUsersServices.read(pageSize, pageNumber);
    }

    @PutMapping("/update-profile")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody UsersDTO usersDTO, BindingResult result) {
        Users users = null;

        Map<String, Object> response  = new HashMap<>();

        if (result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(e -> "El campo "+ e.getField() + " "+ e.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("Error: ", errors);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            users = iUsersServices.updateProfile(usersDTO);
        }catch (DataAccessException err){
            response.put("message", "Error al actualizar un usuario en la base de datos");
            response.put("Error", err.getMessage()+ ": "+ err.getMostSpecificCause().getMessage());
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "usuario ha sido actualizado exitosamente");
        response.put("users", users);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/update-estado")
    void updateEstado(@RequestBody EstadoRequest estadoRequest) {
        iUsersServices.updateEstadoUser(estadoRequest.getEstado(), estadoRequest.getId());
    }

    @PostMapping("/upload-imagen")
    void uploadImagen(@RequestBody UploadImagenRequest uploadImagenRequest) {
        iUsersServices.uploadImagenUser(uploadImagenRequest.getImagen(), uploadImagenRequest.getId());
    }

    @GetMapping("/count-users")
    Integer countUsers() {
        return iUsersServices.countUsers();
    }

    @GetMapping("/count-usersInactive")
    Integer countUsersInactive() {
        return iUsersServices.countUsersInactive();
    }

    @GetMapping("/count-usersActive")
    Integer countUsersActive() {
        return iUsersServices.countUsersActive();
    }
}
