package app.model.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Check {
    private int id;
    BigDecimal sum;
    private Timestamp timestamp;
    private int userId;
    private int orderId;
    private CheckStatus statusEnum;
    private CheckStatusUa statusUaEnum;

    public enum CheckStatus {
        UNPAID, PAID
    }
    public enum CheckStatusUa {
        НЕОПЛАЧЕНИЙ, ОПЛАЧЕНИЙ
    }

    public CheckStatus getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(CheckStatus statusEnum) {
        this.statusEnum = statusEnum;
    }

    public CheckStatusUa getStatusUaEnum() {
        return statusUaEnum;
    }

    public void setStatusUaEnum(CheckStatusUa statusUaEnum) {
        this.statusUaEnum = statusUaEnum;
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
                ", sum=" + sum +
                ", userId=" + userId +
                ", orderId=" + orderId +
                '}';
    }
}
