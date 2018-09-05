package app.model.dao.impl;

import app.model.dao.IOrderHasDishDao;
import app.model.entity.OrderHasDish;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderHasDishDaoImpl implements IOrderHasDishDao {
    private Connection connection;

    public OrderHasDishDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public OrderHasDish insert(OrderHasDish entity) {
        String sql = "INSERT INTO order_has_dish (amount,dish_id,order_id) values(?,?,?) ";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,entity.getAmount());
            preparedStatement.setInt(2,entity.getDishId());
            preparedStatement.setInt(3,entity.getOrderId());
            if(preparedStatement.executeUpdate()>0){
                return entity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public OrderHasDish findById(int id) {
        return null;
    }

    @Override
    public Iterable<OrderHasDish> getAll() {
        return null;
    }

    @Override
    public boolean update(OrderHasDish entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteByDishOrderId(int dishId, int orderId) {
        String sql = "delete from order_has_dish where order_id="+orderId+" AND dish_id="+dishId;
        String deleteOrder = " update `order` set status='deleted', status_ua='видалений' " +
                "where id="+orderId+" AND id not in (select order_id from order_has_dish)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql);
        PreparedStatement preparedStatementDelete = connection.prepareStatement(deleteOrder)) {
            int resDeleteOrderHasDish = preparedStatement.executeUpdate();
            int resDeleteOrder = preparedStatementDelete.executeUpdate();
            return resDeleteOrderHasDish > 0 || resDeleteOrder > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
