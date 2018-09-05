package app.model.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Order {
    private int id;
    private BigDecimal sum;
    private Timestamp timestamp;
    private int userId;
    private int adminId;

    private Map<Dish,Integer> dishAmount;
    private Check check;
    private User user;
    private OrderStatus statusEnum;
    private OrderStatusUa statusUaEnum;

    public enum OrderStatus {
        CONFIRMED, UNCONFIRMED,DELETED
    }
    public enum OrderStatusUa {
        ПІДТВЕРДЖЕНИЙ, НЕПІДТВЕРДЖЕНИЙ, ВИДАЛЕНИЙ
    }

    public Order(){
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderStatus getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(OrderStatus statusEnum) {
        this.statusEnum = statusEnum;
    }

    public OrderStatusUa getStatusUaEnum() {
        return statusUaEnum;
    }

    public void setStatusUaEnum(OrderStatusUa statusUaEnum) {
        this.statusUaEnum = statusUaEnum;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    public Map<Dish, Integer> getDishAmount() {
        return dishAmount;
    }

    public void setDishAmount(Map<Dish, Integer> dishAmount) {
        this.dishAmount = dishAmount;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", sum=" + sum +
                ", timestamp=" + timestamp +
                ", userId=" + userId +
                ", adminId=" + adminId +
                ", dishAmount=" + dishAmount +
                '}';
    }
}
