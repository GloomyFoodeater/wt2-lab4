package controller.commands.impl;

import controller.RequestContext;
import controller.RequestContextHelper;
import controller.commands.Command;
import controller.commands.CommandResult;
import controller.commands.CommandResultType;
import entity.Apartment;
import entity.User;
import entity.UserInfo;
import entity.UserOrder;
import service.ServiceFactory;
import service.interfaces.ApartmentInterface;
import service.interfaces.UserInfoInterface;
import service.interfaces.UserInterface;
import service.interfaces.UserOrderInterface;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GoToViewOrdersCommand implements Command {

    private static final String PAGE 				= "WEB-INF/view/viewOrders.jsp";
    private static final String ERROR_PAGE 			= "WEB-INF/view/error.jsp";
    private static final String USER_ORDERS 		= "userOrders";
    private static final String USERS 				= "users";
    private static final String APARTMENTS 			= "apartments";
    private static final String USER_INFORMATION	= "userInformation";
    private static final String EXPECTED 			= "bloked";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();
        try {
            UserOrderInterface userOrderService = ServiceFactory.getInstance().getUserOrderService();
            List<UserOrder> userOrders = userOrderService.retrieveUserOrderByStatus(EXPECTED);
            requestContext.addRequestAttributes(USER_ORDERS, userOrders);

            UserInterface userService = ServiceFactory.getInstance().getUserService();
            List<User> users = userService.getUsersFromOrders(userOrders);
            requestContext.addRequestAttributes(USERS, users);

            ApartmentInterface apartmentService=ServiceFactory.getInstance().getApartmentService();
            List<Apartment> apartments=apartmentService.retrieveApartmentsByUserOrders(userOrders);
            requestContext.addRequestAttributes(APARTMENTS, apartments);

            UserInfoInterface userInformationService = ServiceFactory.getInstance().getUserInfoService();
            List<UserInfo> userInformation = userInformationService.getUserInfoFromUsers(users);
            requestContext.addRequestAttributes(USER_INFORMATION, userInformation);
        } catch (Exception e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }
        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
}
