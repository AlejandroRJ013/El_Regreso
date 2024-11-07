package com.el_regreso.lista_usuarios.service;

import org.springframework.stereotype.Service;

import com.el_regreso.lista_usuarios.exception.DuplicateUserException;
import com.el_regreso.lista_usuarios.exception.InvalidInputException;
import com.el_regreso.lista_usuarios.exception.UserNotFoundException;
import com.el_regreso.lista_usuarios.model.User;

import java.util.*;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();

    public UserService() {
        users.add(new User(1L, "Alejandro", "alejandro@example.com"));
        users.add(new User(2L, "Maria", "maria@example.com"));
        users.add(new User(3L, "Carlos", "carlos@example.com"));
        users.add(new User(4L, "Lucia", "lucia@example.com"));
        users.add(new User(5L, "Juan", "juan@example.com"));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public User createUser(User user) {
        user.setId(ultimoId());
        revisionUsuario(user);
        users.add(user);

        return user;
    }

    public User updateUser(Long id, User updatedUser) {
        User user = getUserById(id);
        users.remove(user);
        revisionUsuario(updatedUser);
        if (user != null) {
            user.setId(updatedUser.getId());
            user.setNombre(updatedUser.getNombre());
            user.setEmail(updatedUser.getEmail());
            users.add(user);

            return user;
        }
        return null;
    }

    public void deleteUser(Long id) {
        User user = getUserById(id);
        users.remove(user);
    }

    private Long ultimoId() {
        return users.stream()
                .mapToLong(User::getId)
                .max()
                .orElse(0L) + 1;
    }

    private void revisionUsuario(User user) {
        if (user.getId() < 1) {
            throw new InvalidInputException("El ID debe ser mayor a 0");
        } else if (users.stream().anyMatch(u -> u.getId().equals(user.getId()))) {
            throw new InvalidInputException("El ID no puede repetirse");
        }
        user.setNombre(user.getNombre().substring(0, 1).toUpperCase() + user.getNombre().substring(1).toLowerCase());
        if (users.stream().anyMatch(u -> u.getEmail().equals(user.getEmail()))) {
            throw new DuplicateUserException("El correo electrónico ya está en uso: " + user.getEmail());
        }
    }
}
