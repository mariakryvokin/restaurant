package app.model.entity;

import java.util.List;

public class OrderHasDish {
    private int id;
    private int amount;
    private int orderId;
    private int dishId;

    private List<Dish> dish;
    private Order order;

    public OrderHasDish(){

    }

    public OrderHasDish(int amount, int orderId, int dishId) {
        this.amount = amount;
        this.orderId = orderId;
        this.dishId = dishId;
    }

    public List<Dish> getDish() {
        return dish;
    }

    public void setDish(List<Dish> dish) {
        this.dish = dish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
