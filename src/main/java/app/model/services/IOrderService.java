package app.model.services;

import app.model.entity.Order;

import java.math.BigDecimal;
import java.util.List;

public interface IOrderService extends IGenericService<Order>{
    List<Order> getAllNotConfirmed();
    BigDecimal sum(Order order);

}
