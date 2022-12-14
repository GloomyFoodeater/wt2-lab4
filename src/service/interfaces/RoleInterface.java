package service.interfaces;

import entity.Role;

import java.util.Optional;

public interface RoleInterface {
    Optional<Role> retrieveRoleById(int roleId) throws Exception;
    Optional<Role> retrieveRoleByRoleName(String roleName) throws Exception;
}
