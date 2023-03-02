package com.touch.blankspot.user.portal.service;

import com.touch.blankspot.user.portal.data.model.RoleUsers;
import com.touch.blankspot.user.portal.data.repository.RoleUsersRepository;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleUsersService {

  @NonNull private final RoleUsersRepository roleUsersRepository;

  public void save(List<RoleUsers> users) {
    roleUsersRepository.saveAll(users);
  }
}
