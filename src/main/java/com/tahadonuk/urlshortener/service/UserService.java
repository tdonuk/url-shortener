package com.tahadonuk.urlshortener.service;

import com.tahadonuk.urlshortener.data.entity.User;
import com.tahadonuk.urlshortener.data.repository.UserRepository;
import com.tahadonuk.urlshortener.exception.UserConflictException;
import com.tahadonuk.urlshortener.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;

    public User getById(long id) throws Exception {
        if(userRepo.existsById(id)) {
            return userRepo.findById(id).get();
        } else throw new UserNotFoundException("No user found with given id '" + id + "'");
    }

    public void saveUser(User user) throws UserConflictException {
        if(! userRepo.existsByUsername(user.getUsername())) {
            userRepo.save(user);
        } else throw new UserConflictException("A user with given username '" + user.getUsername() + "' is already exists");
    }

}
