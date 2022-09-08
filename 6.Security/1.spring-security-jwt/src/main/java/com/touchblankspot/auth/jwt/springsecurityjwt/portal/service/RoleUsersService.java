package com.touchblankspot.auth.jwt.springsecurityjwt.portal.service;

import com.touchblankspot.auth.jwt.springsecurityjwt.portal.data.model.RoleUsers;
import com.touchblankspot.auth.jwt.springsecurityjwt.portal.data.repository.RoleUsersRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleUsersService {

  @NonNull private final RoleUsersRepository roleUsersRepository;

  public void save(List<RoleUsers> users) {
    roleUsersRepository.saveAll(users);
  }
}
