package com.GameList.controllers;


import com.GameList.Repository.RoleRepository;
import com.GameList.Repository.UsersRepository;
import com.GameList.models.Enumeration.ERole;
import com.GameList.models.Role;
import com.GameList.models.Users;
import com.GameList.payload.request.LoginRequest;
import com.GameList.payload.request.SignupRequest;
import com.GameList.payload.response.JwtResponse;
import com.GameList.payload.response.MessageResponse;
import com.GameList.security.jwt.JwtUtils;
import com.GameList.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/singin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    userDetails.getImagen(),
                    roles));
        }catch (Exception e) {
            return ResponseEntity.status(200).body(new MessageResponse("Usuario o contraseña incorrecto"));
        }



    }

    @PostMapping("/admin/signup")
    public ResponseEntity<?> registerUserAdmin(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Este nombre de usuario ya esta en uso!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: El correo Electronico ya existe!"));
        }
        if (userRepository.existsByCedula(signUpRequest.getCedula())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: La cedula ya existe!"));
        }

        // Create new user's account
        Users user = new Users(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
               signUpRequest.getEmail(), signUpRequest.getNombres(),
               signUpRequest.getApellidos(), signUpRequest.getCedula(),
               signUpRequest.getCelular(), signUpRequest.getFechaNacimiento(),
               signUpRequest.getImagen(), signUpRequest.getEdad(), signUpRequest.getEstado());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Usuario Registrado Correctamente!"));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Este nombre de usuario ya esta en uso!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: El correo Electronico ya existe!"));
        }
        if (userRepository.existsByCedula(signUpRequest.getCedula())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: La cedula ya existe!"));
        }

        // Create new user's account
        Users user = new Users(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEmail(), signUpRequest.getNombres(),
                signUpRequest.getApellidos(), signUpRequest.getCedula(),
                signUpRequest.getCelular(), signUpRequest.getFechaNacimiento(),
                signUpRequest.getImagen(), signUpRequest.getEdad(), signUpRequest.getEstado());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Usuario Registrado Correctamente!"));
    }

}
