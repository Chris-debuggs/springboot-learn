package com.springbootdemo.springbootlearn.service;

import com.springbootdemo.springbootlearn.model.User;
import com.springbootdemo.springbootlearn.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String username, String rawPassword) {
        if (userRepository.existsById(username)) {
            throw new IllegalArgumentException("User already exists");
        }
        userRepository.save(new User(username, passwordEncoder.encode(rawPassword)));
    }

    public User authenticate(String username, String rawPassword) {
        Optional<User> userOpt = userRepository.findById(username);
        if (userOpt.isPresent() && passwordEncoder.matches(rawPassword, userOpt.get().getPassword())) {
            return userOpt.get();
        }
        return null;
    }
}
