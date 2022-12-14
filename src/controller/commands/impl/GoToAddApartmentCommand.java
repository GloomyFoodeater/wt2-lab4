package controller.commands.impl;

import controller.RequestContext;
import controller.RequestContextHelper;
import controller.commands.Command;
import controller.commands.CommandResult;
import controller.commands.CommandResultType;

import javax.servlet.http.HttpServletResponse;

public class GoToAddApartmentCommand implements Command {

    public static final String PAGE = "WEB-INF/view/appApartment.jsp";

    public CommandResult execute (RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();
        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.REDIRECT);
    }
}
