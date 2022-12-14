package controller.commands.impl;

import controller.RequestContext;
import controller.RequestContextHelper;
import controller.commands.Command;
import controller.commands.CommandResult;
import controller.commands.CommandResultType;
import service.ServiceFactory;
import service.interfaces.UserOrderInterface;

import javax.servlet.http.HttpServletResponse;

public class CompleteOrderCommand implements Command {

    private static final String PAGE 			= "command=viewOrders";
    private static final String USER_ORDER_ID 	= "userOrderId";
    private static final String ERROR_PAGE 		= "WEB-INF/view/error.jsp";
    private static final String CONFIRMED 		= "confirmed";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();
        try {
            int userOrderId = Integer.parseInt(requestContext.getRequestParameter(userOrderId));
            UserOrderInterface userOrderService = ServiceFactory.getInstance().getUserOrderService();
            userOrderService.updateStatusAtUserOrderById(userOrderId, CONFIRMED);
        }catch (Exception e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }
        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.REDIRECT);
    }

}
