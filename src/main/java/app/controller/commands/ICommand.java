package app.controller.commands;

import javax.servlet.http.HttpServletRequest;

public interface ICommand {
    String execute(HttpServletRequest req, String method);
}
