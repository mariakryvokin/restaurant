package app.controller.commands.admin;

import app.config.IRegexContainer;
import app.controller.commands.ICommand;
import app.exceptions.ExistsException;
import app.exceptions.HttpException;
import app.exceptions.WrongInputException;
import app.model.entity.Order;
import app.model.services.Services;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class OrderToConfirm implements ICommand {
    @Override
    public String execute(HttpServletRequest req, String method) throws HttpException {
        if(method.equals("get")){
            String login = req.getParameter("login");
            String orderId= req.getParameter("orderId");
            if(login!=null && !login.equals("")){
                return showUnconfirmedOrdersByLogin(login.trim(),req);
            }
            else if(orderId!=null && !orderId.equals("")){
                return showUnconfirmedOrderById(orderId.trim(),req);
            }
            req.setAttribute("ordersToConfirm", Services.ORDER_SERVICE.getAllUnconfirmed().values());
            return "/WEB-INF/admin/ordersToConfirm.jsp";
        }
        if(method.equals("post")){
            Order order = Services.ORDER_SERVICE.findById(Integer.parseInt(req.getParameter("orderId")));
            System.out.println("ORDER TO CONFIRM"+ order);
            order.setAdminId((Integer) req.getSession(false).getAttribute("userId"));
            Services.ORDER_SERVICE.confirm(order);
            return "/admin/ordersToConfirm";
        }
        return null;
    }

    private String showUnconfirmedOrderById(String orderId, HttpServletRequest req) throws WrongInputException {
        List<Order> orders = new ArrayList<>();
        Order order = Services.ORDER_SERVICE.findById(Integer.parseInt(orderId),"unconfirmed");
        if(order==null){
            throw new WrongInputException(400,"this order has deleted or confirmed status");
        }
        orders.add(order);
        req.setAttribute("ordersToConfirm",orders);
        return "/WEB-INF/admin/ordersToConfirm.jsp";
    }

    private String showUnconfirmedOrdersByLogin(String login, HttpServletRequest req) throws HttpException {
        if(!Services.USER_SERVICE.isStringCorrect(login,IRegexContainer.REGEX_LOGIN)){
            throw new WrongInputException(400,"wrong login input");
        }
        if(!Services.USER_SERVICE.exists(login)){
            throw new ExistsException(400,"such login doesn't exist");
        }
        req.setAttribute("ordersToConfirm",Services.ORDER_SERVICE.getAll(
                Services.USER_SERVICE.getByLogin(login),
                "unconfirmed","en").values());
        return "/WEB-INF/admin/ordersToConfirm.jsp";
    }
}
