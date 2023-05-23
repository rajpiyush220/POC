package com.learn.react.backend.service;

import com.learn.react.backend.data.domain.User;
import com.learn.react.backend.data.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__({ @Autowired}))
public class UserService {

    @NonNull
    private final UserRepository userRepository;


    public User register(User user){
        if(user.getUserId() == null){
            user.setUserId(UUID.randomUUID());
        }
        return userRepository.save(user);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public Optional<User> authenticateUser(String username, String password){
        return userRepository.findByUserNameAndPassword(username,password);
    }
}
