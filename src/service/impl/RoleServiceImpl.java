package service.impl;

import dao.DAOfactory;
import dao.interfaces.RoleDAO;
import entity.Role;
import service.interfaces.RoleInterface;

import java.util.Optional;

public class RoleServiceImpl implements RoleInterface {
    public Optional<Role> retrieveRoleById(int roleId) throws Exception {
        try {
            RoleDAO roleDAO = DAOfactory.getInstance().getRoleDAO();
            return roleDAO.findById(roleId);
        }catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    public Optional<Role> retrieveRoleByRoleName(String roleName) throws Exception {
        try {
            RoleDAO roleDAO = DAOfactory.getInstance().getRoleDAO();
            return roleDAO.findByRole(roleName);
        }catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }
}
