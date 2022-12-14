package service;

import service.impl.*;
import service.interfaces.*;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final ApartmentInterface apartmentService = new ApartmentServiceImpl();
    private final RoleInterface roleService = new RoleServiceImpl();
    private final UserInfoInterface userInformationService = new UserInfoServiceImpl();
    private final UserOrderInterface userOrderService = new UserOrderServiceImpl();
    private final UserInterface userService = new UserServiceImpl();


    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public ApartmentInterface getApartmentService() {
        return apartmentService;
    }

    public RoleInterface getRoleService() {
        return roleService;
    }

    public UserInfoInterface getUserInfoService() {
        return userInformationService;
    }

    public UserInterface getUserService() {
        return userService;
    }

    public UserOrderInterface getUserOrderService() {
        return userOrderService;
    }
}
