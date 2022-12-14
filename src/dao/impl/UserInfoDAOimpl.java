package dao.impl;

import dao.AbstractDAOimpl;
import dao.Table;
import dao.interfaces.UserInfoDAO;
import dao.map.RowMapperFactory;
import entity.UserInfo;

public class UserInfoDAOimpl extends AbstractDAOimpl<UserInfo> implements UserInfoDAO {

    private static final String SAVE_USER_INFORMATION_QUERY = "INSERT INTO " + Table.USER_INFORMATION + " (name,  phone) VALUES (?, ?)";
    private static final String UPDATE_BY_ID 				= "UPDATE "+ Table.USER_INFORMATION +"  SET name=?, WHERE account_id=?";

    public UserInfoDAOimpl() {
        super(RowMapperFactory.getInstance().getUserInformationRowMapper(), Table.USER_INFORMATION);
    }

    @Override
    public int save(UserInfo userInformation) throws Exception {
        return executeInsertQuery(SAVE_USER_INFORMATION_QUERY, userInformation.getName());
    }

    @Override
    public void updateById(int id, UserInfo userInformation) throws Exception {
        executeUpdateQuery(UPDATE_BY_ID,userInformation.getName(),id);
    }
}
