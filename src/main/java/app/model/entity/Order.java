package app.model.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Order {
    private int id;
    private BigDecimal sum;
    private Timestamp timestamp;
/*    private LocalDateTime dateTime;*/
    private String status;
    private int userId;
    private int adminId;

    private Map<Dish,Integer> dishAmount;

    public Order(){

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
                "sum=" + sum +
                ", timestamp=" + timestamp +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                '}';
    }
}
