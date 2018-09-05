package app.controller.commands.admin;

import app.controller.commands.ICommand;
import app.model.entity.Order;
import app.model.services.Services;

import javax.servlet.http.HttpServletRequest;

public class OrderSetDeletedStatus implements ICommand {
    @Override
    public String execute(HttpServletRequest req, String method) {
        if(method.equals("post")){
            Order order = Services.ORDER_SERVICE.findById(Integer.parseInt(req.getParameter("orderId")));
            order.setAdminId((Integer) req.getSession(false).getAttribute("userId"));
            Services.ORDER_SERVICE.changeStatusToDeleted(order);
            return "/admin/ordersToConfirm";
        }
        return null;
    }
}
