package app.model.services;

import app.model.entity.OrderHasDish;

import java.util.Map;

public interface IOrderHasDish extends IGenericService<OrderHasDish> {
  boolean deleteByDishOrderId(int dishId, int orderId);
}
