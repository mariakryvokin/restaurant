package app.model.dao.impl;

import app.controller.commands.Login;
import app.model.dao.IOrderDao;
import app.model.dao.mapper.OrderMapper;
import app.model.entity.Check;
import app.model.entity.Dish;
import app.model.entity.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements IOrderDao {
    private static final Logger logger = LogManager.getLogger(Login.class.getName());
    private Connection connection;

    public OrderDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Order> getAllNotConfirmed() {
        List<Order> orders = new ArrayList<>();
        String sql = "Select * FROM restaurant.`order` where status='notConfirmed'";
        try(Statement st = connection.createStatement();ResultSet rs = st.executeQuery(sql)) {
            OrderMapper orderMapper = new OrderMapper();
            while (rs.next()){
                Order order = orderMapper.extractFromResultSet(rs);
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Order insert(Order order) {
        String insertOrderSql = "INSERT INTO restaurant.`order` (`order`.sum,date_time,user_id) VALUES (?,?,?)";
        String insertOrderHasDishSql ="INSERT INTO order_has_dish (amount,dish_id,order_id) values(?,?,?) ";
        try{
            connection.setAutoCommit(false);
            try( PreparedStatement insertOrder = connection.prepareStatement(insertOrderSql, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement insertOrderHasDish= connection.prepareStatement(insertOrderHasDishSql)){
                insertOrder.setBigDecimal(1, order.getSum());
                insertOrder.setTimestamp(2,order.getTimestamp());
                insertOrder.setInt(3, order.getUserId());
                insertOrder.executeUpdate();
                try( ResultSet generatedKeys = insertOrder.getGeneratedKeys()) {
                    generatedKeys.next();
                    order.setId(generatedKeys.getInt(1));
                }
                for (Dish dish : order.getDishAmount().keySet()) {
                    insertOrderHasDish.setInt(1, order.getDishAmount().get(dish));
                    insertOrderHasDish.setInt(2, dish.getId());
                    insertOrderHasDish.setInt(3, order.getId());
                    if(insertOrderHasDish.executeUpdate()>0){
                        connection.commit();
                    }
                }
            }
            connection.setAutoCommit(true);
            logger.info("Order"+ order+"for user"+order.getUserId() +"has been created");
            return order;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    logger.warn("Transaction is being rolled back");
                    connection.rollback();
                    } catch (SQLException e1) {
                        logger.error(e);
                    }
                }
                logger.error(e);
            }
        return null;
    }

        @Override
    public Order findById(int id) {
            String sql = "Select * FROM restaurant.`order` WHERE id="+ id;
            try(Statement st = connection.createStatement();ResultSet rs = st.executeQuery(sql)) {
                OrderMapper orderMapper = new OrderMapper();
                if (rs.next()){
                    return orderMapper.extractFromResultSet(rs);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
    }

    @Override
    public Iterable<Order> getAll() {
        return null;
    }

    @Override
    public synchronized boolean update(Order order) {
        System.out.println("DAO ORDER UPDATE");
        String sqlOrder = "UPDATE restaurant.`order` set admin_id ="+ order.getAdminId()+",status='confirmed' where id="+ order.getId();
        String sqlCheck = "INSERT INTO restaurant.`check`(date_time,`sum`,order_id,user_id) values(?,?,?,?)";
        try {
            if (!connection.createStatement().executeQuery("SELECT id FROM restaurant.`order` WHERE id = " + order.getId() + " AND status = 'notConfirmed'").next()) {
                return false;
            }
            connection.setAutoCommit(false);
            System.out.println("Transaction isolation ORDER UPDAE"+ connection.getTransactionIsolation());
            try (Statement statement = connection.createStatement();
                 PreparedStatement preparedStatement= connection.prepareStatement(sqlCheck)) {
                statement.executeUpdate(sqlOrder);
                Check check = new Check();
                check.setUserId(order.getUserId());
                check.setTimestamp(Timestamp.valueOf(LocalDateTime.now()));
                check.setSum(order.getSum());
                check.setOrderId(order.getId());

                preparedStatement.setTimestamp(1,check.getTimestamp());
                preparedStatement.setBigDecimal(2,check.getSum());
                preparedStatement.setInt(3,check.getOrderId());
                preparedStatement.setInt(4,check.getUserId());
                if( preparedStatement.executeUpdate()>0){
                    connection.commit();
                }
            }
            logger.info("order "+order.getId()+" confirmed successfully");
            return true;
        }catch (SQLException e) {
            if (connection != null) {
                try {
                    logger.warn("Transaction is being rolled back");
                    connection.rollback();
                } catch (SQLException e1) {
                    logger.error(e1);
                }
            }
            logger.error(e);
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void close() {
        try {
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
