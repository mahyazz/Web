package com.zm.a1.controller;

import com.zm.a1.model.User;
import com.zm.a1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/users")
    public ResponseEntity<String> activateUser(@RequestParam String username, @RequestParam boolean active) {
        userService.updateUserStatus(username, active);
        return ResponseEntity.ok("User status updated successfully");
    }
}
