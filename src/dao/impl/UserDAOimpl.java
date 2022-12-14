package dao.impl;

import dao.AbstractDAOimpl;
import dao.Table;
import dao.interfaces.UserDAO;
import dao.map.RowMapperFactory;
import entity.User;

import java.util.Optional;

public class UserDAOimpl extends AbstractDAOimpl<User> implements UserDAO {

    private static final String FIND_USER_BY_EMAIL 	= "SELECT * FROM " + Table.USER + " WHERE email=?";
    private static final String SAVE_USER_QUERY 	= "INSERT INTO " + Table.USER + " (email, role_id, userInformation_id) VALUES (?, ?, ?)";
    private static final String DELETE_USER_QUERY 	= "DELETE FROM " + Table.USER + " WHERE id=?";

    public UserDAOimpl() {
        super(RowMapperFactory.getInstance().getUserRowMapper(), Table.USER);
    }

    @Override
    public Optional<User> findByEmail(String email) throws Exception {
        return executeQueryForSingleResult(FIND_USER_BY_EMAIL, email);
    }

    @Override
    public int save(User user) throws Exception {
        return executeInsertQuery(SAVE_USER_QUERY,
                user.getRoleId(), user.getUserInformationId());
    }

    @Override
    public void removeById(int id) throws Exception {
        executeUpdateQuery(DELETE_USER_QUERY, id);
    }
}
