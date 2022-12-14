package dao;

import dao.impl.*;

public class DAOfactory {

    private static final DAOfactory instance = new DAOfactory();

    private DAOfactory() {}

    public static DAOfactory getInstance() {
        return instance;
    }

    private final UserDAOimpl userDao = new UserDAOimpl();
    private final RoleDAOimpl roleDao = new RoleDAOimpl();
    private final UserInfoDAOimpl userInfoDao = new UserInfoDAOimpl();
    private final UserOrderDAOimpl userOrderDao=new UserOrderDAOimpl();
    private final ApartmentDAOimpl apartmentDao=new ApartmentDAOimpl();

    public UserDAOimpl getUserDAO() {
        return userDao;
    }

    public RoleDAOimpl getRoleDAO() {
        return roleDao;
    }

    public UserInfoDAOimpl getUserInformationDAO() {
        return userInfoDao;
    }

    public UserOrderDAOimpl getUserOrderDAO() {
        return userOrderDao;
    }

    public ApartmentDAOimpl getApartmentDAO() {
        return apartmentDao;
    }
}
