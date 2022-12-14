package dao.impl;

import dao.AbstractDAOimpl;
import dao.Table;
import dao.interfaces.RoleDAO;
import dao.map.RowMapperFactory;
import entity.Role;

import java.util.List;
import java.util.Optional;

public class RoleDAOimpl extends AbstractDAOimpl<Role> implements RoleDAO {

    private static final String SAVE_ROLE_QUERY 		= "INSERT INTO " + Table.ROLE + " (role) VALUES (?)";
    private static final String FIND_ROLE_BY_NAME_QUERY = "SELECT * FROM " + Table.ROLE + " WHERE role=?";

    public RoleDAOimpl() {
        super(RowMapperFactory.getInstance().getRoleRowMapper(), Table.ROLE);
    }

    @Override
    public int save(Role role) throws Exception {
        return executeInsertQuery(SAVE_ROLE_QUERY, role.getRole());
    }

    @Override
    public Optional<Role> findByRole(String role) throws Exception {
        return executeQueryForSingleResult(FIND_ROLE_BY_NAME_QUERY, role);
    }
}
