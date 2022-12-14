package controller.commands.impl;

import controller.RequestContext;
import controller.RequestContextHelper;
import controller.commands.Command;
import controller.commands.CommandResult;
import controller.commands.CommandResultType;

import javax.servlet.http.HttpServletResponse;

public class GoToAddUserOrderCommand implements Command {

    private static final String PAGE = "WEB-INF/view/addUserOrder.jsp";
    private static final String APARTMENT_ID="apartment_id";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();
        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
}
