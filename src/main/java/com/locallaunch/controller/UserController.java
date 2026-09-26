package com.locallaunch.controller;
import jakarta.validation.Valid;
import com.locallaunch.dto.LoginRequest;
import com.locallaunch.dto.LoginResponse;
import com.locallaunch.entity.User;
import com.locallaunch.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public LoginResponse loginUser(
            @RequestBody LoginRequest loginRequest) {

        return userService.loginUser(loginRequest);
    }
}