package service.impl;

import dao.DAOfactory;
import dao.interfaces.UserOrderDAO;
import entity.UserOrder;
import service.interfaces.UserOrderInterface;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class UserOrderServiceImpl implements UserOrderInterface {
    private static final String status = "blocked";

    public Optional<UserOrder> retrieveUserOrderById(int userOrderId) throws Exception {
        try {
            UserOrderDAO userOrderDAO = DAOfactory.getInstance().getUserOrderDAO();
            return userOrderDAO.findById(userOrderId);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    public List<UserOrder> retrieveUserOrderByUserId(int userId) throws Exception {
        try {
            UserOrderDAO userOrderDAO = DAOfactory.getInstance().getUserOrderDAO();
            return userOrderDAO.findByUserId(userId);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    public List<UserOrder> retrieveUserOrderByStatus(String status) throws Exception {
        try {
            UserOrderDAO userOrderDAO = DAOfactory.getInstance().getUserOrderDAO();
            return userOrderDAO.findByStatus(status);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    public boolean updateStatusAtUserOrderById(int userOrderId, String status) throws Exception {
        try {
            UserOrderDAO userOrderDao = DAOfactory.getInstance().getUserOrderDAO();
            Optional<UserOrder> userOrder = userOrderDao.findById(userOrderId);
            if (userOrder.isEmpty()) {
                return false;
            }
            userOrderDao.updateStatusById(userOrderId, status);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    public boolean addNewUserOrder( String stringYear,String stringMonth,String stringDay,String stringHours,
                                    String stringMinutes, String stringLeaseDuration,String stringUserId,String stringApartmentId) throws Exception {

        if ( stringYear == null  || stringMonth==null || stringHours == null ||
                stringLeaseDuration==null || stringUserId==null || stringApartmentId==null ) {
            return false;
        }

        int year, month, day, hours, minutes, leaseDuration, userId, apartmentId;
        try {
            year=Integer.parseInt(stringYear);
            month=Integer.parseInt(stringMonth);
            day=Integer.parseInt(stringDay);
            hours=Integer.parseInt(stringHours);
            minutes=Integer.parseInt(stringMinutes);
            leaseDuration=Integer.parseInt(stringLeaseDuration);
            userId=Integer.parseInt(stringUserId);
            apartmentId=Integer.parseInt(stringApartmentId);

            Timestamp currentDateTime = new Timestamp(System.currentTimeMillis());
            Timestamp orderDateTime = new Timestamp(year-1900, month-1, day, hours, minutes, 0 ,0);
            if(!isDateTimeValid(currentDateTime,orderDateTime)){
                return false;
            }
        }catch (NumberFormatException e) {
            throw new Exception(e.getMessage(), e);
        }
        try {
            UserOrder userOrder = buildUserOrder(leaseDuration, userId,apartmentId);
            UserOrderDAO userOrderDAO = DAOfactory.getInstance().getUserOrderDAO();
            userOrderDAO.save(userOrder);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
    }

    private boolean isDateTimeValid(Timestamp currentDateTime, Timestamp orderDateTime) {
        return currentDateTime.compareTo(orderDateTime) <= 0;
    }

    private UserOrder buildUserOrder(int leaseDuration, int userId, int apartmentId) {
        UserOrder userOrder = new UserOrder();
        userOrder.setStatus(UserOrderServiceImpl.status);
        userOrder.setLeaseDuration(leaseDuration);
        userOrder.setUserId(userId);
        userOrder.setApartmentId(apartmentId);
        return userOrder;
    }
}
