package com.touchblankspot.auth.jwt.springsecurityjwt.portal.data.repository;

import com.touchblankspot.auth.jwt.springsecurityjwt.portal.data.model.RoleUsers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleUsersRepository extends CrudRepository<RoleUsers, UUID> {}
