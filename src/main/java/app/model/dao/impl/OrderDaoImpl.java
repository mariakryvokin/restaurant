package app.model.dao.impl;

import app.controller.commands.Login;
import app.model.dao.IOrderDao;
import app.model.dao.mapper.OrderCheckMapper;
import app.model.dao.mapper.OrderDishMapper;
import app.model.dao.mapper.OrderMapper;
import app.model.entity.Check;
import app.model.entity.Dish;
import app.model.entity.Order;
import app.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class OrderDaoImpl implements IOrderDao {
    private static final Logger logger = LogManager.getLogger(Login.class.getName());
    private Connection connection;

    public OrderDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Map<Integer, Order> getAllUnconfirmed() {
        Map<Integer,Order> orderMap = new HashMap<>();
        String sql = "Select `order`.id as 'order.id',`order`.sum, `order`.date_time, " +
                "amount, " +
                "dish.id as 'dish.id', name_ua, dish.name, price, category_id, " +
                " `user`.login " +
                "FROM restaurant.`order`,order_has_dish,dish,restaurant.`user` where status='unconfirmed' AND " +
                "restaurant.`order`.id=order_has_dish.order_id AND dish.id=order_has_dish.dish_id AND `order`.user_id=`user`.id ";
        try(Statement st = connection.createStatement();ResultSet rs = st.executeQuery(sql)) {
            OrderDishMapper orderDishMapper = new OrderDishMapper();
            while (rs.next()){
                Order order = orderDishMapper.extractFromResultSet(rs);
                if(orderMap.putIfAbsent(order.getId(),order)!=null){
                    orderMap.get(order.getId()).getDishAmount().putAll(order.getDishAmount());
                }
            }
            return orderMap;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Order> getAll(User user) {
        List<Order> orders = new ArrayList<>();
        String sql="SELECT `order`.id as 'order.id',`order`.sum as 'order.sum', `order`.date_time as 'order.date_time'," +
                "`order`.status as 'order.status',`order`.status_ua as 'order.status_ua',`order`.user_id as 'order.user_id', " +
                "`order`.admin_id as 'order.admin_id'," +
                "`check`.id as 'check.id',`check`.status as 'check.status', `check`.status_ua as 'check.status_ua'," +
                " `check`.date_time as 'check.date_time'," +
                " `check`.sum as 'check.sum', `check`.order_id as'check.order_id'," +
                "`check`.user_id as 'check.user_id' from `order` left join `check` on `check`.order_id=`order`.id " +
                "where `order`.user_id="+user.getId()+" ORDER by `order`.date_time DESC ";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            OrderCheckMapper orderCheckMapper = new OrderCheckMapper();
            while (resultSet.next()){
                Order order = orderCheckMapper.extractFromResultSet(resultSet);
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map<Integer, Order> getAll(User user, String status, String language) {
        String statusForSql= language.equals("ua") ? "status_ua" : "status";
        Map<Integer,Order> orderMap = new HashMap<>();
        String sql = "Select `order`.id as 'order.id',`order`.sum, `order`.date_time, " +
                "amount," +
                "dish.id as 'dish.id', name_ua, dish.name, price, category_id, `user`.login " +
                "FROM restaurant.`order`,order_has_dish,dish,restaurant.`user` " +
                "where restaurant.`order`.id=order_has_dish.order_id " +
                "AND dish.id=order_has_dish.dish_id AND `order`.user_id=`user`.id  AND `order`.user_id= "+user.getId()+
                " AND " + statusForSql + "='"+status + "'";
        try(PreparedStatement preparedStatement= connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            OrderDishMapper orderDishMapper = new OrderDishMapper();
            while (resultSet.next()){
                Order order = orderDishMapper.extractFromResultSet(resultSet);
                if(orderMap.putIfAbsent(order.getId(),order)!=null){
                    orderMap.get(order.getId()).getDishAmount().putAll(order.getDishAmount());
                }
            }
            return orderMap;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public synchronized Order changeStatusToDeleted(Order order) {
        String sql = "UPDATE restaurant.`order` SET status='deleted',status_ua='видалений', admin_id = "+ order.getAdminId()+ " where id= "+ order.getId();
        try(PreparedStatement preparedStatement= connection.prepareStatement(sql)) {
            if(connection.createStatement().executeQuery("SELECT id FROM restaurant.`order` WHERE id = " + order.getId() + " AND (status='deleted' OR status='confirmed')").next()) {
                logger.info("try to changeStatusToDeleted already changed!");
                return order;
            }
            if(preparedStatement.executeUpdate()>0){
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
    public Order findById(int id, String status) {
        String sql = "Select `order`.id as 'order.id',`order`.sum, `order`.date_time, " +
                "amount," +
                "dish.id as 'dish.id', name_ua, dish.name, price, category_id, `user`.login " +
                "FROM restaurant.`order`,order_has_dish,dish,restaurant.`user` " +
                "where restaurant.`order`.id=order_has_dish.order_id " +
                "AND dish.id=order_has_dish.dish_id AND `order`.user_id=`user`.id AND status='"+ status +"' AND `order`.id ="+ id;
        try(PreparedStatement preparedStatement= connection.prepareStatement(sql);ResultSet rs = preparedStatement.executeQuery()) {
            OrderDishMapper orderDishMapper= new OrderDishMapper();
            rs.next();
            Order order = orderDishMapper.extractFromResultSet(rs);
            while (rs.next()){
                order.getDishAmount().putAll(orderDishMapper.extractFromResultSet(rs).getDishAmount());
            }
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Order findById(int id) {
        String sql = "Select `order`.id as 'order.id',`order`.sum, status, status_ua, " +
                "amount," +
                "dish.id as 'dish.id', name_ua, dish.name, price,`user`.id  as 'user.id'" +
                "FROM restaurant.`order`,order_has_dish,dish,restaurant.`user`" +
                "where restaurant.`order`.id=order_has_dish.order_id " +
                "AND dish.id=order_has_dish.dish_id AND `order`.user_id=`user`.id AND `order`.id ="+ id;
        try(PreparedStatement preparedStatement= connection.prepareStatement(sql);ResultSet rs = preparedStatement.executeQuery()) {
            OrderMapper orderMapper= new OrderMapper();
            rs.next();
            Order order = orderMapper.extractFromResultSet(rs);
            while (rs.next()){
                order.getDishAmount().putAll(orderMapper.extractFromResultSet(rs).getDishAmount());
            }
            return order;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public synchronized boolean confirm(Order order) {
        String sqlOrder = "UPDATE restaurant.`order` set admin_id ="+ order.getAdminId()+",status='confirmed',status_ua='підтверджений' where id="+ order.getId();
        String sqlCheck = "INSERT INTO restaurant.`check`(date_time,`sum`,order_id,user_id) values(?,?,?,?)";
        try {
            if (!connection.createStatement().executeQuery("SELECT id FROM restaurant.`order` WHERE id = " + order.getId() + " AND status = 'unconfirmed'").next()) {
                logger.info("try to confirm already changed!");
                return false;
            }
            connection.setAutoCommit(false);
            System.out.println("Transaction isolation ORDER UPDATE"+ connection.getTransactionIsolation());
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
    public Iterable<Order> getAll() {
        return null;
    }

    @Override
    public synchronized boolean update(Order order) {
      return false;
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
