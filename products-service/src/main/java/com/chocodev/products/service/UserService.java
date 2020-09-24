package com.chocodev.products.service;

import com.chocodev.products.model.entity.User;
import com.chocodev.products.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String email) {
        User user = new User();
        user.setEmail(email);
        userRepository.save(user);
        return user;
    }
}
