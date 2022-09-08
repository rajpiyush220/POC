package com.touchblankspot.auth.jwt.springsecurityjwt.portal.service;

import com.touchblankspot.auth.jwt.springsecurityjwt.portal.data.model.User;
import com.touchblankspot.auth.jwt.springsecurityjwt.portal.data.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

  @NonNull private final UserRepository userRepository;

  public List<User> findAll() {
    return StreamSupport.stream(userRepository.findAll().spliterator(), true).toList();
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  public User findByUserName(String userName) {
    return userRepository.findByUserName(userName);
  }

  public User finalByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public User findByContactNo(String contactNo) {
    return userRepository.findByContactNo(contactNo);
  }

  public List<User> findByRoles(String role) {
    return StreamSupport.stream(userRepository.findAll().spliterator(), true)
        .filter(user -> user.getRoles().contains(role))
        .toList();
  }
}
