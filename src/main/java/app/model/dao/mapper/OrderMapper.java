package app.model.dao.mapper;


import app.model.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements ObjectMapper<Order> {
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
        return order;
    }

}
