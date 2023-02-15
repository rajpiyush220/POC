package com.touchblankspot.service.inventory.data.enums;

import com.touchblankspot.service.inventory.type.util.RoleSelectType;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import lombok.Getter;

@Getter
public enum RoleEnum {
  SUPER_ADMIN("super_admin", "Super Admin"),
  ADMIN("admin", "Admin"),
  MANAGER("manager", "Manager"),
  SUPERVISOR("supervisor", "Supervisor"),
  USER("user", "User"),
  STAFF("staff", "Staff");

  private final String roleName;
  private final String displayName;
  private static List<RoleSelectType> roleSelectTypes;

  RoleEnum(String name, String displayName) {
    this.roleName = name;
    this.displayName = displayName;
  }

  public static List<RoleSelectType> getRoleSelectTypes() {
    if (roleSelectTypes == null) {
      roleSelectTypes =
          Arrays.stream(RoleEnum.values())
              .filter(
                  role ->
                      !role.name().equals(SUPER_ADMIN.name()) && !role.name().equals(ADMIN.name()))
              .map(roleEnum -> new RoleSelectType(roleEnum.name(), roleEnum.displayName))
              .sorted(Comparator.comparing(RoleSelectType::displayName))
              .toList();
    }
    return roleSelectTypes;
  }
}
