package app.controller.commands.user;

import app.controller.commands.ICommand;
import app.model.entity.Dish;
import app.model.services.Services;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;

public class DeleteFromOrder implements ICommand {
    @Override
    public String execute(HttpServletRequest req, String method) {
        if (method.equals("post")) {
            int removeId = Integer.parseInt(req.getParameter("idToRemove"));
            Dish dish = Services.DISH_SERVICE.findById(removeId);
            Map<Dish, Integer> dishObjMap = (Map<Dish, Integer>) req.getSession(false).getAttribute("dishObjMap");
            dishObjMap.remove(dish);
            req.getSession().setAttribute("dishObjMap", dishObjMap);
            return "/user/showOrder";
        }
        return null;
    }
}
