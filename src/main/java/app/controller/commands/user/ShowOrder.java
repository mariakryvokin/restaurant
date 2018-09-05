package app.controller.commands.user;

import app.controller.commands.ICommand;
import app.model.entity.Dish;
import app.model.entity.Order;
import app.model.services.Services;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ShowOrder implements ICommand {
    @Override
    @SuppressWarnings("unchecked")
    public String execute(HttpServletRequest req, String method) {
        if(method.equals("post")){
            return"/user/showOrder";
        }
        if(method.toLowerCase().equals("get")){
            HashMap<Dish, Integer> dishObjMap = (HashMap<Dish, Integer>) req.getSession().getAttribute("dishObjMap");
            Order order = new Order();
            order.setDishAmount(dishObjMap);
            req.setAttribute("totalPrice", Services.ORDER_SERVICE.sum(order));
            req.setAttribute("DishAmountEntrySet",dishObjMap.entrySet());
            return "/WEB-INF/user/order.jsp";
        }
      return null;
    }
}
