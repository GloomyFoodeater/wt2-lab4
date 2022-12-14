package dao.map.impl;

import dao.map.Column;
import dao.map.RowMapper;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(Column.ID));
        user.setRoleId(resultSet.getInt(Column.ROLE_ID));
        user.setUserInformationId(resultSet.getInt(Column.USER_INFORMATION_ID));
        return user;
    }
}
