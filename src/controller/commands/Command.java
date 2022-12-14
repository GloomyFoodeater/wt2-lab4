package controller.commands;

import controller.RequestContextHelper;

import javax.servlet.http.HttpServletResponse;

public interface Command {

    public CommandResult execute(RequestContextHelper helper, HttpServletResponse response);
}
