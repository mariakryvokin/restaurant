package app.model.dao.impl;

import app.model.dao.ICategoryDao;
import app.model.dao.mapper.CategoryMapper;
import app.model.entity.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements ICategoryDao {
    private Connection connection;

    public CategoryDaoImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public Category insert(Category entity) {
        return null;
    }

    @Override
    public Category findById(int id) {
        return null;
    }

    @Override
    public Iterable<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        String sql = "Select * FROM category";
        try(Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql)) {
            CategoryMapper categoryMapper = new CategoryMapper();
            while (rs.next()){
                Category category = categoryMapper.extractFromResultSet(rs);
                categories.add(category);
            }
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Category entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
