package app.model.dao;

import app.model.entity.Order;

import java.math.BigDecimal;
import java.util.List;

public interface IOrderDao extends IGenericDao<Order> {
    List<Order> getAllNotConfirmed();
}
