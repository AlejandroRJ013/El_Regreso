package com.el_regreso.lista_usuarios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.el_regreso.lista_usuarios.exception.DuplicateUserException;
import com.el_regreso.lista_usuarios.exception.InvalidInputException;
import com.el_regreso.lista_usuarios.exception.UserNotFoundException;
import com.el_regreso.lista_usuarios.model.User;
import com.el_regreso.lista_usuarios.repository.UserRepository;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserService() {
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User createUser(User user) {
        revisionUsuario(user);
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        User user = getUserById(id);
        revisionUsuario(updatedUser);
        user.setNombre(updatedUser.getNombre());
        user.setEmail(updatedUser.getEmail());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    private void revisionUsuario(User user) {
        // if (user.getId() < 1) {
        //     throw new InvalidInputException("El ID debe ser mayor a 0");
        // }
        if (userRepository.existsById(user.getId())) {
            throw new InvalidInputException("El ID no puede repetirse");
        }
        user.setNombre(user.getNombre().substring(0, 1).toUpperCase() + user.getNombre().substring(1).toLowerCase());
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateUserException("El correo electrónico ya está en uso: " + user.getEmail());
        }
    }
}
