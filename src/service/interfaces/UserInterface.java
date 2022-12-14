package service.interfaces;

import entity.User;
import entity.UserOrder;

import java.util.List;
import java.util.Optional;

public interface UserInterface {
    Optional<User> login(String email) throws Exception;
    boolean register(String name, String email, String phone) throws Exception;
    Optional<User> retrieveUserById(int userId) throws Exception;
    List<User> getUsersFromOrders(List<UserOrder> orders) throws Exception;
}
