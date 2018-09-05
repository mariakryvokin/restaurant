package app.controller.commands.user;

import app.controller.commands.ICommand;
import app.model.entity.Dish;
import app.model.services.Services;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class AddToOrder implements ICommand {
    @Override
    public String execute(HttpServletRequest req, String method) {
        if (method.equals("post")) {
            int dishId = Integer.parseInt(req.getParameter("dishId"));
            int amount = Integer.parseInt(req.getParameter("amount"));
            HashMap <Dish, Integer> dishObjMap = (HashMap<Dish, Integer>) req.getSession(false).getAttribute("dishObjMap");
            if (dishObjMap == null) {
                dishObjMap = new HashMap<>();
            }
            Dish dish = Services.DISH_SERVICE.findById(dishId);
            dishObjMap.put(dish,amount);
            req.getSession(false).setAttribute("dishObjMap", dishObjMap);
            return "/user/menu?category_id="+dish.getCategoryId();
        }
        return null;
    }
}