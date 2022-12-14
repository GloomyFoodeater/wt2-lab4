package controller.commands.impl;

import controller.RequestContext;
import controller.RequestContextHelper;
import controller.commands.Command;
import controller.commands.CommandResult;
import controller.commands.CommandResultType;
import entity.User;
import entity.UserInfo;
import service.ServiceFactory;
import service.interfaces.UserInfoInterface;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class GoToProfileCommand implements Command {

    private static final String PAGE 				= "WEB-INF/view/profile.jsp";
    private static final String ERROR_PAGE 			= "WEB-INF/view/error.jsp";
    private static final String USER 				= "user";
    private static final String USER_INFORMATION 	= "userInformation";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();
        User user = (User) requestContext.getSessionAttribute(USER);
        if (user == null) {
            helper.updateRequest(requestContext);
            return new CommandResult(PAGE, CommandResultType.FORWARD);
        }
        try {
            int userInformationId = user.getUserInformationId();
            UserInfoInterface userInformationService = ServiceFactory.getInstance().getUserInfoService();
            Optional<UserInfo> userInformation = userInformationService.retrieveUserInfoById(userInformationId);
            userInformation.ifPresent(information -> requestContext.addRequestAttributes(USER_INFORMATION, information));
        } catch (Exception e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }
        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }

}
