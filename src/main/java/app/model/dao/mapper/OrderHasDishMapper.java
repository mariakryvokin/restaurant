package app.model.dao.mapper;

import app.model.entity.OrderHasDish;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class OrderHasDishMapper implements ObjectMapper<OrderHasDish>{

    @Override
    public OrderHasDish extractFromResultSet(ResultSet rs) throws SQLException {
        OrderHasDish orderHasDish = new OrderHasDish();
        orderHasDish.setId(rs.getInt("id"));
        orderHasDish.setAmount(rs.getInt("amount"));
        orderHasDish.setDishId(rs.getInt("dish_id"));
        orderHasDish.setOrderId(rs.getInt("order_id"));
        return orderHasDish;
    }

/*    public OrderHasDishImpl insertIntoTable(PreparedStatement preparedStatement) throws SQLException {
        OrderHasDishImpl entity = new OrderHasDishImpl();
        preparedStatement.setInt(1,entity.getAmount());
        preparedStatement.setInt(2,entity.getDishId());
        preparedStatement.setInt(3,entity.getOrderId());
        if(preparedStatement.executeUpdate()>0){
            return entity;
        }
        return null;
    }*/
}
