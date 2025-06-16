package com.spring.User_Management_API.service;

import com.spring.User_Management_API.model.User;
import com.spring.Library_Management_API.ResourceNotFoundException;
import com.spring.User_Management_API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

   @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${logging.level.root}")
    private String logLevel;

    @Value("${server.port}")
    private String serverPort;

    public void displayConfigurations() {
        System.out.println("DataSource URL: " + dataSourceUrl);
        System.out.println("Log Level: " + logLevel);
        System.out.println("Server port: " + serverPort);
    }

    public User createUser(User book) {
        return userRepository.save(book);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));
    }

    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}