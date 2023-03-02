package com.touch.blankspot.user.portal.data.repository;

import com.touch.blankspot.user.portal.data.model.Role;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, UUID> {

  Set<Role> findAllByNameIn(List<String> roles);
}
