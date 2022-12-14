package controller.commands.impl;

import controller.RequestContext;
import controller.RequestContextHelper;
import controller.commands.Command;
import controller.commands.CommandResult;
import controller.commands.CommandResultType;
import entity.Apartment;
import entity.User;
import entity.UserOrder;
import service.ServiceFactory;
import service.interfaces.ApartmentInterface;
import service.interfaces.UserOrderInterface;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GoToMyOrdersCommand implements Command {

    private static final String PAGE 		= "WEB-INF/view/myOrders.jsp";
    private static final String ERROR_PAGE 	= "WEB-INF/view/error.jsp";
    private static final String USER_ORDERS	= "userOrders";
    private static final String APARTMENTS 	= "apartments";
    private static final String USER 		= "user";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        User user = (User) requestContext.getSessionAttribute(USER);
        if (user == null) {
            helper.updateRequest(requestContext);
            return new CommandResult(PAGE, CommandResultType.FORWARD);
        }
        try {
            UserOrderInterface userOrderService= ServiceFactory.getInstance().getUserOrderService();
            List<UserOrder> userOrders=userOrderService.retrieveUserOrderByUserId(user.getId());
            requestContext.addRequestAttributes(USER_ORDERS, userOrders);
            ApartmentInterface apartmentService=ServiceFactory.getInstance().getApartmentService();
            List<Apartment> apartments=apartmentService.retrieveApartmentsByUserId(user.getId());
            requestContext.addRequestAttributes(APARTMENTS, apartments);

        } catch (Exception e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
}
