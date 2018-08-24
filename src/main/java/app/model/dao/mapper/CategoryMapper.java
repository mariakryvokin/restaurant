package app.model.dao.mapper;

import app.model.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CategoryMapper implements ObjectMapper<Category>{
    @Override
    public Category extractFromResultSet(ResultSet rs) throws SQLException {
        Category category = new Category();
        category.setId(rs.getInt("id"));
        category.setName(rs.getString("name"));
        category.setNameUa(rs.getString("name_ua"));
        return category;
    }

}
