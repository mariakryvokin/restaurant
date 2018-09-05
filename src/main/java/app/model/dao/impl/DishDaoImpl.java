package app.model.dao.impl;

import app.model.dao.IDishDao;
import app.model.dao.mapper.DishMapper;
import app.model.entity.Dish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DishDaoImpl implements IDishDao {
    private Connection connection;


    public DishDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Dish insert(Dish dish) {
        String sql = "INSERT into dish (`name`, name_ua, price, category_id)  values(?,?,?,?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,dish.getName());
            preparedStatement.setString(2,dish.getNameUa());
            preparedStatement.setInt(3,dish.getId());
            if(preparedStatement.executeUpdate()>1)
                return dish;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Dish findById(int id) {
        String sql = "Select * FROM dish WHERE id="+ id;
        try(Statement st = connection.createStatement();ResultSet rs = st.executeQuery(sql)) {
            DishMapper dishMapper = new DishMapper();
            if (rs.next()){
                return dishMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Dish> getAll() {
        List<Dish> dishes = new ArrayList<>();
        String sql = "Select * FROM dish join category on dish.category_id=category.id";
        try(Statement st = connection.createStatement();ResultSet rs = st.executeQuery(sql)) {
            DishMapper dishMapper = new DishMapper();
            while (rs.next()){
                Dish dish = dishMapper.extractFromResultSet(rs);
                dishes.add(dish);
            }
            return dishes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Dish entity) {
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

    @Override
    public Iterable<Dish> findDishForPagination(int currentPage, int recordsPerPage, int category_id) {
        ArrayList<Dish> dishes = new ArrayList<>();
        String sql = "SELECT * FROM dish WHERE category_id="+category_id+" LIMIT ?, ?";
        try( PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,currentPage);
            preparedStatement.setInt(2, recordsPerPage);
            DishMapper dishMapper = new DishMapper();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                dishes.add(dishMapper.extractFromResultSet(rs));
            }
            return dishes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int countCategoryDishes(int category_id) {
        String sql = "Select count(id)as 'amount' FROM dish WHERE category_id="+category_id;
        try(Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql)) {
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("cant get count category dishes");
    }

    @Override
    public Dish findByName(String dishName, String language) {
        String nameForSql= language.equals("ua") ? "name_ua" : "name";
        String sql = "Select * from dish where "+nameForSql+"="+dishName;
        DishMapper dishMapper = new DishMapper();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            Dish dish = dishMapper.extractFromResultSet(preparedStatement.executeQuery());
            return dish;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}
