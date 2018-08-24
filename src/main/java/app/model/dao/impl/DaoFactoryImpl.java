package app.model.dao.impl;

import app.config.ConnectionPoolHolder;
import app.model.dao.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactoryImpl extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    @Override
    public IDishDao createDishDao() {
        return new DishDaoImpl(getConnection());
    }

    @Override
    public IUserDao createUserDao() {
        return new UserDaoImpl(getConnection());
    }

    @Override
    public ICategoryDao createCategoryDao() {
        return new CategoryDaoImpl(getConnection());
    }

    @Override
    public IOrderDao createOrderDao() {
        return new OrderDaoImpl(getConnection());
    }

    @Override
    public IOrderHasDishDao createOrderHasDishDao() {
        return new OrderHasDishDaoImpl(getConnection());
    }

    @Override
    public ICheckDao createCheckDao() {
        return new CheckDaoImpl(getConnection());
    }

    private Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
