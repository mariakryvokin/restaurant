package app.model.dao.mapper;

import app.model.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OrderDishMapper implements ObjectMapper<Order> {
    @Override
    public Order extractFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("order.id"));
        order.setSum(rs.getBigDecimal("sum"));
        order.setTimestamp(rs.getTimestamp("date_time"));
        Dish dish = new Dish();
        dish.setId(rs.getInt("dish.id"));
        dish.setName(rs.getString("name"));
        dish.setNameUa(rs.getString("name_ua"));
        dish.setPrice(rs.getBigDecimal("price"));
        dish.setCategoryId(rs.getInt("category_id"));
        Map<Dish, Integer> map = new HashMap<>();
        map.put(dish,rs.getInt("amount"));
        order.setDishAmount(map);
        User user = new User();
        user.setLogin(rs.getString("login"));
        order.setUser(user);
        return order;
    }
}
