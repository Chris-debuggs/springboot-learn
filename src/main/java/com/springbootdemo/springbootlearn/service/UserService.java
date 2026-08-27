package com.springbootdemo.springbootlearn.service;

import com.springbootdemo.springbootlearn.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {
    private final Map<String, User> users = new ConcurrentHashMap<>();
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String username, String rawPassword) {
        if (users.containsKey(username)) {
            throw new IllegalArgumentException("User already exists");
        }
        users.put(username, new User(username, passwordEncoder.encode(rawPassword)));
    }

    public User authenticate(String username, String rawPassword) {
        User user = users.get(username);
        if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
            return user;
        }
        return null;
    }
}
