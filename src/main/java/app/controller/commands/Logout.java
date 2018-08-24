package app.controller.commands;

import javax.servlet.http.HttpServletRequest;

public class Logout implements ICommand {
    @Override
    public String execute(HttpServletRequest req, String method) {
        req.getSession().invalidate();
        return  "/";
    }

}
