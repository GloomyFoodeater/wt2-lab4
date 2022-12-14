package controller.commands;

import controller.commands.impl.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandFactory {
    private static final CommandFactory instance = new CommandFactory();
    private static final Map<String, Command> commandsMap = new HashMap<>();

    private CommandFactory() {
        commandsMap.put(CommandName.DEFAULT_COMMAND, new DefaultCommand());
        commandsMap.put(CommandName.MAIN_COMMAND, new GoToMainCommand());
        commandsMap.put(CommandName.PROFILE_COMMAND, new GoToProfileCommand());
        commandsMap.put(CommandName.REGISTRATION_COMMAND, new LogUpCommand());
        commandsMap.put(CommandName.LOG_IN_COMMAND, new GoToLogInCommand());
        commandsMap.put(CommandName.CONTACTS_COMMAND, new GoToContactsCommand());
        commandsMap.put(CommandName.CATALOG_COMMAND, new GoToCatalogCommand());
        commandsMap.put(CommandName.CHECK_LOGIN_COMMAND, new LogInCommand());
        commandsMap.put(CommandName.LOG_OUT_COMMAND, new LogOutCommand());
        commandsMap.put(CommandName.LOG_UP_COMMAND, new GoToLogUpCommand());
        commandsMap.put(CommandName.ADD_APARTMENT_COMMAND, new GoToAddApartmentCommand());
        commandsMap.put(CommandName.CONFIRM_ADDING_APARTMENT_COMMAND, new ConfirmAddingApartmentCommand());
        commandsMap.put(CommandName.MY_ORDERS_COMMAND, new GoToMyOrdersCommand());
        commandsMap.put(CommandName.CONFIRM_USER_ORDER_COMMAND, new ConfirmUserOrderCommand());
        commandsMap.put(CommandName.ADD_USER_ORDER,	new GoToAddUserOrderCommand());
        commandsMap.put(CommandName.COMPLETE_USER_ORDER_COMMAND, new CompleteOrderCommand());
        commandsMap.put(CommandName.EDIT_APARTMENT_STATUS, new GoToChangeApartmentStatusCommand());
        commandsMap.put(CommandName.CONFIRM_CHANGING_APARTMENT_STATUS_COMMAND, new ConfirmChangingApartmentStatusCommand());
        commandsMap.put(CommandName.VIEW_ORDERS_COMMAND, new GoToViewOrdersCommand());
        commandsMap.put(CommandName.DELETE_USER_ORDER_COMMAND, new DeleteUserOrderCommand());
    }

    public static CommandFactory getInstance() {
        return instance;
    }

    public Command getCommand(String name) {
        return Optional.ofNullable(commandsMap.get(name));
    }
}
