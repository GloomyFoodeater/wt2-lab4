package controller.commands.impl;

import controller.RequestContext;
import controller.RequestContextHelper;
import controller.commands.Command;
import controller.commands.CommandResult;
import controller.commands.CommandResultType;

import javax.servlet.http.HttpServletResponse;

public class GoToMainCommand implements Command {

    private static final String PAGE = "WEB-INF/view/main.jsp";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();
        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
}
