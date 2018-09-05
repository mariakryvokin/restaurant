package app.controller.commands.admin;

import app.controller.commands.ICommand;
import app.exceptions.HttpException;
import app.exceptions.WrongInputException;
import app.model.entity.Dish;
import app.model.entity.OrderHasDish;
import app.model.services.Services;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class EditOrder implements ICommand {
    @Override
    public String execute(HttpServletRequest req, String method) throws UnsupportedEncodingException, HttpException {
        if(method.equals("post")){
            String orderId= req.getParameter("orderId");
            System.out.println("POST order id"+ req.getParameter("orderId"));
            if(req.getParameterValues("item")!=null && orderId!=null){
                for(String i:  req.getParameterValues("item")){
                    if(!Services.ORDER_HAS_DISH_SERVICE.deleteByDishOrderId(Integer.valueOf(i), Integer.parseInt(orderId))){
                        return "/admin/ordersToConfirm";
                    }
                }
            }
            return "/admin/editOrder?orderId="+orderId;
        }
        if(method.equals("get")){
            String orderId= req.getParameter("orderId");
            req.setAttribute("orderId",orderId);

            String dishId = req.getParameter("dishId");
            String amount = req.getParameter("dishAmount");
            if(dishId!=null && amount!=null){
                addDishToOrder(Integer.parseInt(dishId),Integer.parseInt(amount), Integer.parseInt(orderId),req);
            }

            req.setAttribute("dishes",Services.DISH_SERVICE.getAll());
            HashMap<Dish,Integer> orderHashMap = (HashMap<Dish, Integer>) Services.ORDER_SERVICE.findById
                    (Integer.parseInt(orderId)).getDishAmount();
            if(!orderHashMap.isEmpty())
            req.setAttribute("editDishSet",orderHashMap);
            return "/WEB-INF/admin/editOrder.jsp";
        }
        return null;
    }
    private void addDishToOrder(int dishId ,int amount, int orderId, HttpServletRequest req) throws WrongInputException {
        OrderHasDish orderHasDish = new OrderHasDish();
        orderHasDish.setAmount(amount);
        orderHasDish.setDishId(dishId);
        orderHasDish.setOrderId(orderId);
        Services.ORDER_HAS_DISH_SERVICE.insert(orderHasDish);
    }

}
