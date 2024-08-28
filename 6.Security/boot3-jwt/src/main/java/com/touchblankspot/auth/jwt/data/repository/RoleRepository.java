package com.touchblankspot.auth.jwt.data.repository;

import com.touchblankspot.auth.jwt.data.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface RoleRepository extends CrudRepository<Role, UUID> {

  Set<Role> findAllByNameIn(List<String> roles);
}
