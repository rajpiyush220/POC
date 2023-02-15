package com.touchblankspot.service.inventory.data.repository;

import com.touchblankspot.service.inventory.data.model.Role;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, UUID> {
  Role findByName(String name);
}
