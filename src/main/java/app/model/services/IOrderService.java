package app.model.services;

import app.model.entity.Order;
import app.model.entity.User;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IOrderService extends IGenericService<Order>{
    Map<Integer, Order> getAllUnconfirmed();
    BigDecimal sum(Order order);
    List<Order> getAll(User user);
    Map<Integer, Order> getAll(User user, String status, String language);
    Order changeStatusToDeleted(Order order);
    Order findById(int id, String status);
    boolean confirm(Order order);
}
