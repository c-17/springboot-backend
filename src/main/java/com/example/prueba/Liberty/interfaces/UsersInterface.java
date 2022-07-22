package com.example.prueba.Liberty.interfaces;

import com.example.prueba.Liberty.models.User;

import java.util.List;

public interface UsersInterface {
    List<User> getUsersPaginated(int page, int size);

    User getUser(Long userId);

    User createUser(User user);

    User updateUser(Long userId, User user);

    Boolean deleteUser(Long userId);
}
