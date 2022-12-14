package controller.commands.impl;

import controller.RequestContext;
import controller.RequestContextHelper;
import controller.commands.Command;
import controller.commands.CommandResult;
import controller.commands.CommandResultType;
import entity.Role;
import entity.User;
import service.ServiceFactory;
import service.interfaces.RoleInterface;
import service.interfaces.UserInterface;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LogInCommand implements Command {

    private static final String PROFILE_PAGE 	= "command=profile";
    private static final String ERROR_PAGE 		= "WEB-INF/view/error.jsp";
    private static final String LOGIN_PAGE 		= "WEB-INF/view/login.jsp";
    private static final String EMAIL_PARAMETER	= "email";
    private static final String USER 			= "user";
    private static final String ROLE 			= "role";
    private static final String ERROR_MESSAGE 	= "errorMessage";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();
        String login = requestContext.getRequestParameter(EMAIL_PARAMETER);
        try {
            UserInterface userService = ServiceFactory.getInstance().getUserService();
            Optional<User> optionalResult = userService.login(login);
            if (optionalResult.isPresent()) {
                requestContext.addSessionAttribute(USER, optionalResult.get());

                RoleInterface roleService = ServiceFactory.getInstance().getRoleService();
                Optional<Role> role = roleService.retrieveRoleById(optionalResult.get().getRoleId());
                role.ifPresent(value -> requestContext.addSessionAttribute(ROLE, value));

                helper.updateRequest(requestContext);
                return new CommandResult(PROFILE_PAGE, CommandResultType.REDIRECT);
            }
        } catch (Exception e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }
        requestContext.addRequestAttributes(ERROR_MESSAGE, true);
        helper.updateRequest(requestContext);
        return new CommandResult(LOGIN_PAGE, CommandResultType.FORWARD);
    }
}
