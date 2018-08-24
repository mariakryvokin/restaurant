package app.controller.commands.user;

import app.controller.commands.ICommand;
import app.model.entity.Dish;
import app.model.entity.Order;
import app.model.entity.OrderHasDish;
import app.model.services.Services;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class SendOrder implements ICommand {
    @Override
    public String execute(HttpServletRequest req, String method) {
        if (method.equals("post")) {
            HashMap<Dish, Integer> dishObjMap = (HashMap<Dish, Integer>) req.getSession().getAttribute("dishObjMap");
            Order order = new Order();
            order.setDishAmount(dishObjMap);
            order.setSum(Services.ORDER_SERVICE.sum(order));
            order.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
            order.setUserId((Integer) req.getSession().getAttribute("userId"));
            Services.ORDER_SERVICE.insert(order);
            req.getSession().removeAttribute("dishObjMap");
            return "/USER/main";
        }
        return null;
    }
}
