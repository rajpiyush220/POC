package com.touch.blankspot.user.portal.service;

import com.touch.blankspot.user.portal.data.model.Role;
import com.touch.blankspot.user.portal.data.repository.RoleRepository;
import java.util.List;
import java.util.Set;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleService {

  @NonNull private final RoleRepository roleRepository;

  public Set<Role> findByName(List<String> roles) {
    return roleRepository.findAllByNameIn(roles);
  }

  public Iterable<Role> findAll() {
    return roleRepository.findAll();
  }
}
