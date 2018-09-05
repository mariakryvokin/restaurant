package app.controller.commands.admin;

import app.config.IRegexContainer;
import app.controller.commands.ICommand;
import app.exceptions.HttpException;
import app.exceptions.WrongInputException;
import app.model.entity.Order;
import app.model.services.Services;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

public class OrdersForUser implements ICommand {
    @Override
    public String execute(HttpServletRequest req, String method) throws UnsupportedEncodingException, HttpException {
        if(method.equals("get")) {
            req.setAttribute("statusEn", Order.OrderStatus.values());
            req.setAttribute("statusUa", Order.OrderStatusUa.values());
            String login =req.getParameter("login");
            String status = req.getParameter("status")!= null ?
                    new String(req.getParameter("status").getBytes("ISO-8859-1"), "UTF-8")  : null ;
            if(login!=null && !login.equals("") && status!=null && !status.equals("")){
                showOrderForUser(login.trim(),status,req);
            }
            return "/WEB-INF/admin/ordersForUser.jsp";
        }
        return null;
    }

    private String showOrderForUser(String login, String status, HttpServletRequest req) throws WrongInputException {
        if(!Services.USER_SERVICE.isStringCorrect(login,IRegexContainer.REGEX_LOGIN))
            throw new WrongInputException(400,"such login exists");
        if(!Services.USER_SERVICE.exists(login)){
            throw new WrongInputException(400,"not correct or not exists");
        }
        req.setAttribute("ordersForUser", Services.ORDER_SERVICE.getAll(Services.USER_SERVICE.getByLogin(login),
                status.toLowerCase(), (String) req.getSession(false).getAttribute("language")).values());
        return "/WEB-INF/admin/ordersForUser.jsp";
    }
}
