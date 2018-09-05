package app.model.dao.mapper;

import app.model.entity.Dish;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DishMapper implements ObjectMapper<Dish> {


    @Override
    public Dish extractFromResultSet(ResultSet rs) throws SQLException {
        Dish dish = new Dish();
        dish.setId(rs.getInt("id"));
        dish.setName(rs.getString("name"));
        dish.setNameUa(rs.getString("name_ua"));
        dish.setPrice(rs.getBigDecimal("price"));
        dish.setCategoryId(rs.getInt("category_id"));
        return dish;
    }

}
