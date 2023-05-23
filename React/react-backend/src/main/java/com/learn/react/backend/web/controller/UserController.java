package com.learn.react.backend.web.controller;

import com.learn.react.backend.data.domain.User;
import com.learn.react.backend.mapper.UserMapper;
import com.learn.react.backend.service.UserService;
import com.learn.react.backend.web.type.AuthenticateUser;
import com.learn.react.backend.web.type.RegisterUserRequest;
import com.learn.react.backend.web.type.UserResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@RequestMapping("/users")
public class UserController {

    @NonNull
    private final UserService userService;

    @NonNull
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterUserRequest request) {
        User user = userService.register(userMapper.toEntity(request));
        return ResponseEntity.ok(userMapper.toApi(user));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(
                userService.getUsers().stream().map(userMapper::toApi).toList()
        );
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> authenticateUser(@RequestBody AuthenticateUser user) {
        Optional<User> authenticatedUser = userService.authenticateUser(user.getUserName(), user.getPassword());
        return authenticatedUser.map(value -> ResponseEntity.ok(userMapper.toApi(value))).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }


}
