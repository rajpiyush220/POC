package com.touchblankspot.auth.jwt.data.repository;

import com.touchblankspot.auth.jwt.data.model.RoleUsers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleUsersRepository extends CrudRepository<RoleUsers, UUID> {}
