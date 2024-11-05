package com.el_regreso.lista_usuarios.service;

import org.springframework.stereotype.Service;

import com.el_regreso.lista_usuarios.model.User;

import java.util.*;

@Service
public class UserService {
    private List<User> users = new ArrayList<>();
    private Long currentId = 1L;

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User createUser(User user) {
        user.setId(currentId++);
        users.add(user);
        return user;
    }

    public User updateUser(Long id, User updatedUser) {
        User user = getUserById(id);
        if (user != null) {
            user.setNombre(updatedUser.getNombre());
            user.setEmail(updatedUser.getEmail());
            return user;
        }
        return null;
    }

    public void deleteUser(Long id) {
        users.removeIf(user -> user.getId().equals(id));
    }
}
