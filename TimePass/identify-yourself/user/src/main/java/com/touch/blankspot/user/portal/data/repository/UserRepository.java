package com.touch.blankspot.user.portal.data.repository;

import com.touch.blankspot.user.portal.data.model.User;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

  User findByUserName(String userName);

  User findByEmail(String email);

  User findByContactNo(String contactNo);

}
