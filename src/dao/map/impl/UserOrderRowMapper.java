package dao.map.impl;

import dao.map.Column;
import dao.map.RowMapper;
import entity.UserOrder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOrderRowMapper implements RowMapper<UserOrder> {

    @Override
    public UserOrder map(ResultSet resultSet) throws SQLException {
        UserOrder userOrder = new UserOrder();
        userOrder.setId(resultSet.getInt(Column.ID));
        userOrder.setApartmentId(resultSet.getInt(Column.APARTMENT_ID));
        userOrder.setLeaseDuration(resultSet.getInt(Column.USER_ORDER_LEASE_DURATION));
        userOrder.setStartTime(resultSet.getTimestamp(Column.USER_ORDER_START_TIME));
        userOrder.setUserId(resultSet.getInt(Column.USER_ID));
        return userOrder;
    }
}
