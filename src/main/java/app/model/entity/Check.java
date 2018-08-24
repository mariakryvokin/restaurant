package app.model.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Check {
    private int id;
    private String status;
    BigDecimal sum;
    private Timestamp timestamp;
    private int userId;
    private int orderId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Check{" +
                "status='" + status + '\'' +
                ", sum=" + sum +
                ", timestamp=" + timestamp +
                ", userId=" + userId +
                ", orderId=" + orderId +
                '}';
    }
}
