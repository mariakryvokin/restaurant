package app.model.services.impl;

import app.model.dao.DaoFactory;
import app.model.dao.IOrderDao;
import app.model.entity.Dish;
import app.model.entity.Order;
import app.model.services.IOrderService;

import java.math.BigDecimal;
import java.util.List;

public class OrderServiceImpl implements IOrderService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public Order insert(Order entity) {
        try( IOrderDao dao = daoFactory.createOrderDao()) {
            return dao.insert(entity);
        }
    }

    @Override
    public Order findById(int id) {
        try(IOrderDao dao = daoFactory.createOrderDao()){
            return dao.findById(id);
        }
    }

    @Override
    public Iterable<Order> getAll() {
        try(IOrderDao dao = daoFactory.createOrderDao()){
            return  dao.getAll();
        }
    }

    @Override
    public boolean update(Order entity) {
        try(IOrderDao dao = daoFactory.createOrderDao()){
          return  dao.update(entity);
        }
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Order> getAllNotConfirmed() {
        try( IOrderDao dao = daoFactory.createOrderDao()) {
            return dao.getAllNotConfirmed();
        }
    }

    @Override
    public BigDecimal sum(Order order) {
        BigDecimal sum=BigDecimal.ZERO;
        for(Dish dish : order.getDishAmount().keySet()){
            sum = sum.add(dish.getPrice().multiply(BigDecimal.valueOf(order.getDishAmount().get(dish))));
        }
        return sum;
    }
}
