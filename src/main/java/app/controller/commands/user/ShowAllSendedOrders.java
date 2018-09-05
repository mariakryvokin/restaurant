package app.controller.commands.user;

import app.controller.commands.ICommand;
import app.model.services.Services;

import javax.servlet.http.HttpServletRequest;

public class ShowAllSendedOrders implements ICommand {
    @Override
    public String execute(HttpServletRequest req, String method) {
        if(method.equals("get")){
            req.setAttribute("allOrders",Services.ORDER_SERVICE.getAll(
                    Services.USER_SERVICE.getByLogin((String) req.getSession(false).getAttribute("login"))));
            return "/WEB-INF/user/allOrders.jsp";
        }
        return null;
    }
}
