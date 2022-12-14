package dao.interfaces;

import dao.DAO;
import entity.Role;

import java.util.Optional;

public interface RoleDAO extends DAO<Role> {

    Optional<Role> findByRole(String role) throws Exception;
}
