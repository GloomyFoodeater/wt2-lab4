package dao.impl;

import dao.AbstractDAOimpl;
import dao.Table;
import dao.interfaces.UserOrderDAO;
import dao.map.RowMapperFactory;
import entity.UserOrder;

import java.util.List;

public class UserOrderDAOimpl extends AbstractDAOimpl<UserOrder> implements UserOrderDAO {

    private static final String FIND_USER_ORDERS_BY_STATUS_QUERY 		= "SELECT * FROM " + Table.USER_ORDER + " WHERE status=?";
    private static final String UPDATE_USER_ORDER_STATUS_BY_ID_QUERY 	= "UPDATE " + Table.USER_ORDER + " SET status=? WHERE id=?";
    private static final String SAVE_USER_ORDER_QUERY 					= "INSERT INTO " + Table.USER_ORDER + " (status, start_time, lease_duration, user_id, apartment_id) VALUES (?, ?, ?, ?, ?)";
    private static final String FIND_USER_ORDERS_BY_USER_ID_QUERY		= "SELECT * FROM " + Table.USER_ORDER + " WHERE user_id=?";
    private static final String FIND_USER_ORDERS_BY_APARTMENT_ID		= "SELECT * FROM " + Table.USER_ORDER + " WHERE apartment_id=?";


    public UserOrderDAOimpl() {
        super(RowMapperFactory.getInstance().getUserOrderRowMapper(), Table.USER_ORDER);
    }

    @Override
    public List<UserOrder> findByStatus(String status) throws Exception {
        return executeQuery(FIND_USER_ORDERS_BY_STATUS_QUERY, status);
    }

    @Override
    public List<UserOrder> findByApartmentId(int apartmentId) throws Exception {
        return executeQuery(FIND_USER_ORDERS_BY_APARTMENT_ID, apartmentId);
    }

    @Override
    public void updateStatusById(int id, String status) throws Exception {
        executeUpdateQuery(UPDATE_USER_ORDER_STATUS_BY_ID_QUERY, status, id);
    }

    @Override
    public int save(UserOrder userOrder) throws Exception {
        return executeInsertQuery(SAVE_USER_ORDER_QUERY, userOrder.getStatus(),userOrder.getStartTime(),
                userOrder.getLeaseDuration(),userOrder.getUserId(),userOrder.getApartmentId());
    }

    @Override
    public List<UserOrder> findByUserId(int userId) throws Exception {
        return executeQuery(FIND_USER_ORDERS_BY_USER_ID_QUERY,userId);
    }

}