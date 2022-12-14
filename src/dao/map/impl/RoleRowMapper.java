package dao.map.impl;

import dao.map.Column;
import dao.map.RowMapper;
import entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role map(ResultSet resultSet) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getInt(Column.ID));
        role.setRole(resultSet.getString(Column.ROLE_ROLE));
        return role;
    }
}
