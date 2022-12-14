package controller.commands.impl;

import controller.RequestContext;
import controller.RequestContextHelper;
import controller.commands.Command;
import controller.commands.CommandResult;
import controller.commands.CommandResultType;
import entity.Apartment;
import entity.User;
import service.ServiceFactory;
import service.interfaces.ApartmentInterface;
import service.interfaces.RoleInterface;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GoToCatalogCommand implements Command {

    private static final String PAGE 		= "WEB-INF/view/catalog.jsp";
    private static final String ERROR_PAGE 	= "WEB-INF/view/error.jsp";
    private static final String APARTMENTS 	= "apartments";
    private static final String STATUS 		= "доступно";
    private static final String USER 		= "user";
    private static final String ADMIN_ROLE 	= "admin";



    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {
        RequestContext requestContext = helper.createContext();

        User user = (User) requestContext.getSessionAttribute(USER);
        if (user == null) {
            helper.updateRequest(requestContext);
            ApartmentInterface apartmentService= ServiceFactory.getInstance().getApartmentService();
            try {
                List<Apartment> apartment=apartmentService.retrieveApartmentByStatus(STATUS);
                requestContext.addRequestAttributes(ADMIN_ROLE, user);
                helper.updateRequest(requestContext);
            } catch (Exception e) {
                return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
            }
            return new CommandResult(PAGE, CommandResultType.FORWARD);
        }
        try {
            RoleInterface roleService=ServiceFactory.getInstance().getRoleService();
            ApartmentInterface apartmentService=ServiceFactory.getInstance().getApartmentService();
            List<Apartment> apartment;
            if (roleService.retrieveRoleById(user.getRoleId()).get().getRole().equals(ADMIN_ROLE)){
                apartment=apartmentService.retrieveAllApartments();
            }else {
                apartment=apartmentService.retrieveApartmentByStatus(STATUS);
            }
            requestContext.addRequestAttributes(APARTMENTS,apartment);
        } catch (Exception e) {
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandResultType.FORWARD);
    }
}
