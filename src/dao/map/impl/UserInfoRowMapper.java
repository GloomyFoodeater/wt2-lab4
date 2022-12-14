package dao.map.impl;

import dao.map.Column;
import dao.map.RowMapper;
import entity.UserInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoRowMapper implements RowMapper<UserInfo> {

    @Override
    public UserInfo map(ResultSet resultSet) throws SQLException {
        UserInfo userInformation = new UserInfo();
        userInformation.setId(resultSet.getInt(Column.ID));
        userInformation.setName(resultSet.getString(Column.USER_INFORMATION_NAME));
        userInformation.setPhone(resultSet.getString(Column.USER_INFORMATION_PHONE));
        return userInformation;
    }
}
