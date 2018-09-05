package app.model.services.impl;

import app.model.dao.DaoFactory;
import app.model.dao.IOrderDao;
import app.model.entity.Dish;
import app.model.entity.Order;
import app.model.entity.User;
import app.model.services.IOrderService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
    public Map<Integer, Order> getAllUnconfirmed() {
        try( IOrderDao dao = daoFactory.createOrderDao()) {
            return dao.getAllUnconfirmed();
        }
    }

    @Override
    public BigDecimal sum(Order order) {
        BigDecimal sum = BigDecimal.ZERO;
        for(Dish dish : order.getDishAmount().keySet()){
            sum = sum.add(dish.getPrice().multiply(BigDecimal.valueOf(order.getDishAmount().get(dish))));
        }
        return sum;
    }

    @Override
    public List<Order> getAll(User user) {
        try(IOrderDao dao = daoFactory.createOrderDao()){
            return dao.getAll(user);
        }
    }

    @Override
    public Map<Integer, Order> getAll(User user, String status, String language) {
        try(IOrderDao dao = daoFactory.createOrderDao()){
            return dao.getAll(user,status,language);
        }
    }

    @Override
    public Order changeStatusToDeleted(Order order) {
        try(IOrderDao dao = daoFactory.createOrderDao()){
            return dao.changeStatusToDeleted(order);
        }
    }

    @Override
    public Order findById(int id, String status) {
        try(IOrderDao dao = daoFactory.createOrderDao()){
            return   dao.findById(id,status);
        }
    }

    @Override
    public boolean confirm(Order order) {
        try(IOrderDao dao =daoFactory.createOrderDao()) {
         return   dao.confirm(order);
        }
    }
}
