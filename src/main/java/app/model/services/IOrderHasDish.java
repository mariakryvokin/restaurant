package app.model.services;

import app.model.entity.OrderHasDish;

import java.util.Map;

public interface IOrderHasDish extends IGenericService<OrderHasDish> {
    Map<Integer,Integer> allDishesForOrder(int dishId, int amount);

}
