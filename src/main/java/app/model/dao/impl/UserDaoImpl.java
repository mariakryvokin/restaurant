package app.model.dao.impl;


import app.model.dao.IUserDao;
import app.model.dao.mapper.UserMapper;
import app.model.entity.User;

import java.sql.*;

public class UserDaoImpl implements IUserDao {
    private Connection connection;

    public UserDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean exists(String login) {
        String existsLoginSQL = "SELECT * FROM user Where login='"+login+"'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(existsLoginSQL)) {
            ResultSet resultSet = preparedStatement.executeQuery(existsLoginSQL);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public User insert(User user) {
        String sql = "INSERT INTO `user` (login,password) VALUES (?,SHA1(?))";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getPassword());
            if(preparedStatement.executeUpdate()>0)return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getByLogin(String login) {
        String sql = "SELECT * FROM `user` Where login='"+login+"'";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery(sql)){
            UserMapper userMapper = new UserMapper();
            if(resultSet.next()) return userMapper.extractFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getByLoginAndPassword(String login, String password) {
        String sql = "SELECT * FROM `user` where login='"+login+"' AND password=SHA1('"+password+"')";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery(sql)) {
            UserMapper userMapper = new UserMapper();
            if(resultSet.next()) return userMapper.extractFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public Iterable<User> getAll() {
        return null;
    }

    @Override
    public boolean update(User entity) {
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
