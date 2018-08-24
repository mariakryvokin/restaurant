package app.model.dao.mapper;

import app.model.entity.Check;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CheckMapper implements ObjectMapper<Check>{
    @Override
    public Check extractFromResultSet(ResultSet rs) throws SQLException {
        Check check = new Check();
        check.setId(rs.getInt("id"));
        check.setOrderId(rs.getInt("order_id"));
        check.setStatus(rs.getString("status"));
        check.setTimestamp(rs.getTimestamp("date_time"));
        check.setSum(rs.getBigDecimal("sum"));
        check.setUserId(rs.getInt("user_id"));
        return check;
    }

}
