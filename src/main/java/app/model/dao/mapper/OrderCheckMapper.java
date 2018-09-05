package app.model.dao.mapper;

import app.model.entity.Check;
import app.model.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderCheckMapper implements ObjectMapper<Order> {
    @Override
    public Order extractFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("order.id"));
        order.setSum(rs.getBigDecimal("order.sum"));
        order.setTimestamp(rs.getTimestamp("order.date_time"));
        order.setUserId(rs.getInt("order.user_id"));
        if(rs.getString("order.admin_id")!=null)
            order.setAdminId(rs.getInt("order.admin_id"));
        order.setStatusEnum(Order.OrderStatus.valueOf(rs.getString("order.status").toUpperCase()));
        order.setStatusUaEnum(Order.OrderStatusUa.valueOf(rs.getString("order.status_ua").toUpperCase()));
        if(rs.getInt("check.id")!=0) {
            Check check = new Check();
            check.setId(rs.getInt("check.id"));
            check.setOrderId(rs.getInt("check.order_id"));
            check.setStatusEnum(Check.CheckStatus.valueOf(rs.getString("check.status").toUpperCase()));
            check.setStatusUaEnum(Check.CheckStatusUa.valueOf(rs.getString("check.status_ua").toUpperCase()));
            check.setTimestamp(rs.getTimestamp("check.date_time"));
            check.setSum(rs.getBigDecimal("check.sum"));
            check.setUserId(rs.getInt("check.user_id"));
            order.setCheck(check);
        }
        return order;
    }
}
