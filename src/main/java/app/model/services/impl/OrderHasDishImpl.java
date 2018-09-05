package app.model.services.impl;


import app.model.dao.DaoFactory;
import app.model.dao.IOrderHasDishDao;
import app.model.entity.OrderHasDish;

import java.util.Map;

public class OrderHasDishImpl implements app.model.services.IOrderHasDish {
    DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public OrderHasDish insert(OrderHasDish entity) {
        try(IOrderHasDishDao dao = daoFactory.createOrderHasDishDao()) {
            return dao.insert(entity);
        }
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
    public boolean deleteByDishOrderId(int dishId, int orderId) {
        try(IOrderHasDishDao dao = daoFactory.createOrderHasDishDao()){
            return dao.deleteByDishOrderId(dishId, orderId);
        }
    }
}
