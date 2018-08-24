package app.model.dao.impl;

import app.model.dao.ICheckDao;
import app.model.dao.mapper.CheckMapper;
import app.model.entity.Check;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CheckDaoImpl implements ICheckDao {
    Connection connection=null;

    public CheckDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Check insert(Check entity) {
        String sql = "INSERT INTO check values(?,?,?)";
        try(PreparedStatement preparedStatement= connection.prepareStatement(sql)) {
            preparedStatement.setTimestamp(1,entity.getTimestamp());
            preparedStatement.setInt(2,entity.getOrderId());
            preparedStatement.setInt(3,entity.getUserId());
            if(preparedStatement.executeUpdate()>0){
                return entity;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Check findById(int id) {
        String sql = "SELECT * FROM restaurant.`check` where id="+ id;
        try (Statement statement = connection.createStatement()) {
            CheckMapper checkMapper = new CheckMapper();
            ResultSet rs = statement.executeQuery(sql);
            if(rs.next()){
                return checkMapper.extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<Check> getAll() {
        return null;
    }

    @Override
    public boolean update(Check entity) {
        String sql = "UPDATE restaurant.`check` set status='paid' where id="+ entity.getId();
        try (PreparedStatement preparedStatement= connection.prepareStatement(sql)) {
            return (preparedStatement.executeUpdate(sql)>0);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
    public List<Check> getAllNotPaidByUserId(int userId) {
        System.out.println("CHECK DAO");
        String sql = "SELECT * from restaurant.`check` where status='notPaid' AND user_id="+userId;
        List<Check> checks= new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            CheckMapper checkMapper = new CheckMapper();
            while (resultSet.next()){
                Check check= checkMapper.extractFromResultSet(resultSet);
                checks.add(check);
            }
            System.out.println("list of checks"+ checks);
            return checks;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
