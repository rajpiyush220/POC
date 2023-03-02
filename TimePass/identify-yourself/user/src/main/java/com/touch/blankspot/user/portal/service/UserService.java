package com.touch.blankspot.user.portal.service;

import com.touch.blankspot.user.portal.data.model.User;
import com.touch.blankspot.user.portal.data.repository.UserRepository;
import java.util.List;
import java.util.stream.StreamSupport;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
