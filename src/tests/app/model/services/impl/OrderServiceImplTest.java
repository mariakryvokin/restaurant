package app.model.services.impl;

import app.model.entity.Dish;
import app.model.entity.Order;
import app.model.services.IOrderService;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class OrderServiceImplTest extends Assert {
    @Test
    public void sumTest(){
        IOrderService iOrderService=new OrderServiceImpl();
        Map<Dish,Integer> dishAmount = new HashMap();
        for(int i =10; i<30; i += 10){
            Dish dish = new Dish();
            dish.setPrice(BigDecimal.valueOf(i));
            dishAmount.put(dish,3);
        }
        BigDecimal res = BigDecimal.valueOf(10*3+20*3);
        Order order = new Order();
        order.setDishAmount(dishAmount);
        assertEquals(res,iOrderService.sum(order));

    }

}