package app.model.dao.mapper;

import app.model.entity.Dish;
import app.model.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OrderMapper implements ObjectMapper<Order> {
    @Override
    public Order extractFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("order.id"));
        order.setUserId(rs.getInt("user.id"));
        order.setSum(rs.getBigDecimal("sum"));
        order.setStatusEnum(Order.OrderStatus.valueOf(rs.getString("status").toUpperCase()));
        order.setStatusUaEnum(Order.OrderStatusUa.valueOf(rs.getString("status_ua").toUpperCase()));
        Dish dish = new Dish();
        dish.setId(rs.getInt("dish.id"));
        dish.setName(rs.getString("name"));
        dish.setNameUa(rs.getString("name_ua"));
        dish.setPrice(rs.getBigDecimal("price"));
        Map<Dish, Integer> map = new HashMap<>();
        map.put(dish,rs.getInt("amount"));
        order.setDishAmount(map);
        return order;
    }
}
