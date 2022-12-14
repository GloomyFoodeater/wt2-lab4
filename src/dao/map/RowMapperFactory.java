package dao.map;

import dao.map.impl.*;
import entity.*;

public class RowMapperFactory {

    private static final RowMapperFactory instance = new RowMapperFactory();

    private RowMapperFactory() {}

    public static RowMapperFactory getInstance() {
        return instance;
    }

    private final RowMapper<Apartment> apartmentRowMapper = new ApartmentRowMapper();
    private final RowMapper<Role> roleRowMapper = new RoleRowMapper();
    private final RowMapper<UserInfo> userInformationRowMapper = new UserInfoRowMapper();
    private final RowMapper<UserOrder> userOrderRowMapper = new UserOrderRowMapper();
    private final RowMapper<User> userRowMapper = new UserRowMapper();

    public RowMapper<Apartment> getApartmentRowMapper() {
        return apartmentRowMapper;
    }

    public RowMapper<Role> getRoleRowMapper() {
        return roleRowMapper;
    }

    public RowMapper<UserInfo> getUserInformationRowMapper() {
        return userInformationRowMapper;
    }

    public RowMapper<UserOrder> getUserOrderRowMapper() {
        return userOrderRowMapper;
    }

    public RowMapper<User> getUserRowMapper() {
        return userRowMapper;
    }
}
