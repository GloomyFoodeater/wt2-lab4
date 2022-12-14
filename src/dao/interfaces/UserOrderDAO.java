package dao.interfaces;

import dao.DAO;
import entity.UserOrder;

import java.util.List;

public interface UserOrderDAO extends DAO<UserOrder> {

    List<UserOrder> findByStatus(String status) throws Exception;
    List<UserOrder> findByUserId(int userId) throws Exception;
    List<UserOrder> findByApartmentId(int apartmentId) throws Exception;
    void updateStatusById(int id, String status) throws Exception;

}
