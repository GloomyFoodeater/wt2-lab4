package service.interfaces;

import entity.UserOrder;

import java.util.List;
import java.util.Optional;

public interface UserOrderInterface {
    Optional<UserOrder> retrieveUserOrderById(int userOrderId) throws Exception;
    List<UserOrder> retrieveUserOrderByUserId(int userId) throws Exception;
    List<UserOrder> retrieveUserOrderByStatus(String status) throws Exception;
    boolean updateStatusAtUserOrderById(int userOrderId, String status) throws Exception;
    boolean addNewUserOrder(String year, String month, String day, String hours,
                            String minutes, String leaseDuration, String userId, String apartmentId) throws Exception;
}
