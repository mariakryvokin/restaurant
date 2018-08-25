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
    private String status;
    private int userId;
    private int adminId;

    private Map<Dish,Integer> dishAmount;

    public Order(){
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId() == order.getId();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
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

/*    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }*/

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
                ", status='" + status + '\'' +
                ", userId=" + userId +
                ", adminId=" + adminId +
                ", dishAmount=" + dishAmount +
                '}';
    }
}
