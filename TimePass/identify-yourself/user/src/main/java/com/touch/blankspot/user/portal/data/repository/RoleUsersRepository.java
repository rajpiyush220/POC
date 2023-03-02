package com.touch.blankspot.user.portal.data.repository;

import com.touch.blankspot.user.portal.data.model.RoleUsers;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleUsersRepository extends CrudRepository<RoleUsers, UUID> {}
