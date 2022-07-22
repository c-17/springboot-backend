package com.example.prueba.Liberty.services;

import com.example.prueba.Liberty.interfaces.UsersInterface;
import com.example.prueba.Liberty.models.User;
import com.example.prueba.Liberty.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class UsersService implements UsersInterface {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<User> getUsersPaginated(int page, int size){
        Pageable paging = PageRequest.of(page, size);

        return usersRepository.findAll(paging).toList();
    }

    @Override
    public User getUser(Long userId) {
        return usersRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "userId not found", null));
    }

    @Override
    public User createUser(User user) {
        return usersRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User updatedUser) {
        User user = usersRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "userId not found", null));

        if(updatedUser.getImageURL() != null)
            user.setImageURL(updatedUser.getImageURL());

        if(updatedUser.getName() != null && !updatedUser.getName().isEmpty() && !Objects.equals(updatedUser.getName(), user.getName()))
            user.setName(updatedUser.getName());

        if(updatedUser.getEmails() != null)
            user.setEmails(updatedUser.getEmails());

        if(updatedUser.getGender() != null && !updatedUser.getGender().isEmpty() && !Objects.equals(updatedUser.getGender(), user.getGender()))
            user.setGender(updatedUser.getGender());

        if(updatedUser.getStatus() != null && !updatedUser.getStatus().isEmpty() && !Objects.equals(updatedUser.getStatus(), user.getStatus()))
            user.setStatus(updatedUser.getStatus());

        usersRepository.save(user);

        return user;
    }

    @Override
    public Boolean deleteUser(Long userId) {
        User user = usersRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "userId not found", null));

        usersRepository.deleteById(userId);

        return true;
    }
}
