package com.touchblankspot.auth.jwt.springsecurityjwt.portal.data.repository;

import com.touchblankspot.auth.jwt.springsecurityjwt.portal.data.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

  User findByUserName(String userName);

  User findByEmail(String email);

  User findByContactNo(String contactNo);

}
