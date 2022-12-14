package controller.commands.impl;

import controller.RequestContext;
import controller.RequestContextHelper;
import controller.commands.Command;
import controller.commands.CommandResult;
import controller.commands.CommandResultType;
import service.ServiceFactory;
import service.interfaces.ApartmentInterface;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class ConfirmAddingApartmentCommand implements Command {

    private static final String PAGE 				= "command=addApartment";
    private static final String ERROR_PAGE 			= "WEB-INF/view/error.jsp";
    private static final String STATUS 				= "status";
    private static final String PRICE 				= "price";
    private static final String APARTMENT_NUMBER 	= "apartmentNumber";
    private static final String MESSAGE_PARAMETER	= "&message=";
    private static final String ERROR 				= "error";
    private static final String OK 					= "ok";

    @Override
    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response) {

        RequestContext requestContext = helper.createContext();
        String message = ERROR;
        Optional<String> status = Optional.ofNullable(requestContext.getRequestParameter(STATUS));
        Optional<String> price = Optional.ofNullable(requestContext.getRequestParameter(PRICE));
        Optional<String> apartmentNumber = Optional.ofNullable(requestContext.getRequestParameter(APARTMENT_NUMBER));
        try {
            if (status.isPresent() && price.isPresent() && apartmentNumber.isPresent()) {
                ApartmentInterface apartmentService = ServiceFactory.getInstance().getApartmentService();
                boolean result = apartmentService.addNewApartment(status.get(), apartmentNumber.get(), price.get());
                if (result) {
                    message = OK;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CommandResult(ERROR_PAGE, CommandResultType.FORWARD);
        }
        helper.updateRequest(requestContext);
        return new CommandResult(PAGE + MESSAGE_PARAMETER + message, CommandResultType.REDIRECT);
    }
}
