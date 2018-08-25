package app.controller.commands.admin;

import app.controller.commands.ICommand;
import app.model.entity.Order;
import app.model.services.Services;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

public class OrderToConfirm implements ICommand {
    @Override
    public String execute(HttpServletRequest req, String method) {
        if(method.equals("get")){
            req.setAttribute("ordersToConfirm", Services.ORDER_SERVICE.getAllNotConfirmed());
            return "/WEB-INF/admin/ordersToConfirm.jsp";
        }
        if(method.equals("post")){
            Order order = Services.ORDER_SERVICE.findById(Integer.parseInt(req.getParameter("orderId")));
            order.setAdminId((Integer) req.getSession(false).getAttribute("userId"));
            Services.ORDER_SERVICE.update(order);
            return "/ADMIN/ordersToConfirm";
        }
        return null;
    }
}
