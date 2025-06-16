package com.spring.User_Management_API.controller;

import com.spring.User_Management_API.model.User;
import com.spring.User_Management_API.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        log.debug("Endpoint createUser");
        if (!user.getEmail().contains("@")) {
            log.warn("User creation failed: email is not correct");
            return ResponseEntity.badRequest().build();
        }

        User createdUser = userService.createUser(user);
        log.info("Created new user with ID {}", createdUser.getId());
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping
    public List<User> getAllUsers() {
        List<User> users = userService.getAllUsers();
        log.debug("Endpoint getAllUsers");
        log.info("Retrieved {} users", users.size());
        return users;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        log.debug("Endpoint getUserById");
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/configurations")
    public ResponseEntity<Void> getConfigurations() {
        log.debug("Endpoint getConfigurations");
        userService.displayConfigurations();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        log.debug("Endpoint updateUser");
        if (!user.getEmail().contains("@")) {
            log.warn("User updated failed: email is not correct");
            return ResponseEntity.badRequest().build();
        }

        User updatedUser = userService.updateUser(id,user);
        log.info("User with ID {} was updated ", updatedUser.getId());
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.debug("Endpoint deleteUser");
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            log.info("Deleted user with ID {}", id);
            return ResponseEntity.noContent().build();
        } else {
            log.warn("Attempt to delete non-existent user with ID {}", id);
            return ResponseEntity.notFound().build();
        }
    }
}


