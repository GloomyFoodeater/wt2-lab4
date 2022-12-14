package controller.commands.impl;

import controller.RequestContext;
import controller.RequestContextHelper;
import controller.commands.Command;
import controller.commands.CommandResult;
import controller.commands.CommandResultType;

import javax.servlet.http.HttpServletResponse;

public class LogOutCommand implements Command {

    private static final String LOGIN_PAGE 	= "command=logIn";
    private static final String USER 		= "user";
    private static final String ROLE 		= "role";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();
        requestContext.removeSessionAttributes(USER);
        requestContext.removeSessionAttributes(ROLE);
        helper.updateRequest(requestContext);
        return new CommandResult(LOGIN_PAGE, CommandResultType.REDIRECT);
    }
}
