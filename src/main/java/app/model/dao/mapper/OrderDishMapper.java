package app.model.dao.mapper;

import app.model.entity.Dish;
import app.model.entity.Order;
import app.model.entity.OrderHasDish;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OrderDishMapper implements ObjectMapper<Order> {
    @Override
    public Order extractFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setSum(rs.getBigDecimal("sum"));
        order.setTimestamp(rs.getTimestamp("date_time"));
        order.setStatus(rs.getString("status"));
        order.setUserId(rs.getInt("user_id"));
        if(rs.getString("admin_id")!=null)
            order.setAdminId(rs.getInt("admin_id"));
        if(rs.getInt("amount")!=-1){
            OrderHasDishMapper orderHasDishMapper = new OrderHasDishMapper();
            DishMapper dishMapper = new DishMapper();
            OrderHasDish orderHasDish= orderHasDishMapper.extractFromResultSet(rs);
            Dish dish = dishMapper.extractFromResultSet(rs);
            Map<Dish, Integer> map = new HashMap<>();
            map.put(dish,orderHasDish.getAmount());
            order.setDishAmount(map);
        }
        return order;
    }
}
