package com.el_regreso.lista_usuarios.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.el_regreso.lista_usuarios.dto.UserDTO;
import com.el_regreso.lista_usuarios.model.User;
import com.el_regreso.lista_usuarios.service.UserService;
import com.el_regreso.lista_usuarios.repository.UserRepository;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users.stream().map(this::convertUserToDto).toList();
    }

    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return convertUserToDto(user);
    }

    @GetMapping("/next-id")
    public Integer getNextId() {
        return userRepository.findNextId();
    }

    @PostMapping
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        System.out.println("Creando usuario con nombre: " + userDTO.getNombre() + " y email: " + userDTO.getEmail());
        User user = convertDtoToUser(userDTO);
        User createdUser = userService.createUser(user);
        System.out.println("Creado");
        return convertUserToDto(createdUser);
    }

    @PutMapping("/{id}")
    public UserDTO updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        User updatedUserEntity = convertDtoToUser(userDTO);
        User updatedUser = userService.updateUser(id, updatedUserEntity);
        return convertUserToDto(updatedUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    private User convertDtoToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setNombre(userDTO.getNombre());
        user.setEmail(userDTO.getEmail());
        return user;
    }

    private UserDTO convertUserToDto(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setNombre(user.getNombre());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}