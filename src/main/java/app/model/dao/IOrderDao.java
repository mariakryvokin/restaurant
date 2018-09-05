package app.model.dao;

import app.model.entity.Order;
import app.model.entity.User;

import java.util.List;
import java.util.Map;
/**
 * @author Mariia Kryvokin
 * @see Map
 * @see List
 */
public interface IOrderDao extends IGenericDao<Order> {
    /**
     * return all unconfirmed orders
     * @return Map<Integer,Order>
     */
    Map<Integer, Order> getAllUnconfirmed();

    /**
     * return list of orders fro user
     * @param user
     * @return List<Order>
     */
    List<Order> getAll(User user);

    /**
     * return all user's orders with concrete status on certain language
     * @param user
     * @param status
     * @param language
     * @return Map<Integer,Order>
     */
    Map<Integer, Order> getAll(User user, String status, String language);

    /**
     * change order status to 'delete'
     * @param order
     * @return Order
     */
    Order changeStatusToDeleted(Order order);

    /**
     * find order by id and status
     * @param id
     * @param status
     * @return Order
     */
    Order findById(int id, String status);

    /**
     * set status to 'confirmed'
     * @param order
     * @return true if status was changed
     */
    boolean confirm(Order order);
}

