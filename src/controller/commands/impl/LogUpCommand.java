package controller.commands.impl;

import controller.RequestContext;
import controller.RequestContextHelper;
import controller.commands.Command;
import controller.commands.CommandResult;
import controller.commands.CommandResultType;
import service.ServiceFactory;
import service.interfaces.UserInterface;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LogUpCommand implements Command {

    private static final String LOG_UP_PAGE	= "WEB-INF/view/logUp.jsp";
    private static final String ERROR_PAGE 	= "WEB-INF/view/error.jsp";
    private static final String EMAIL 		= "email";
    private static final String NAME 		= "name";
    private static final String PHONE 		= "phone";
    private static final String MESSAGE 	= "message";
    private static final String ERROR 		= "error";
    private static final String OK 			= "ok";


    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();
        String message = ERROR;
        Optional<String> email = Optional.ofNullable(requestContext.getRequestParameter(EMAIL));
        Optional<String> name = Optional.ofNullable(requestContext.getRequestParameter(NAME));
        Optional<String> phone = Optional.ofNullable(requestContext.getRequestParameter(PHONE));

        try {
            if (email.isPresent() && name.isPresent() && phone.isPresent()) {
                UserInterface userService = ServiceFactory.getInstance().getUserService();
                boolean result = userService.register(name.get(), email.get(), phone.get());
                if (result) message = OK;
            }
        } catch (Exception e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        requestContext.addRequestAttributes(MESSAGE, message);
        helper.updateRequest(requestContext);
        return new CommandResult(LOG_UP_PAGE, CommandResultType.FORWARD);
    }
}
