package app.model.services.impl;

import app.model.dao.DaoFactory;
import app.model.dao.IUserDao;
import app.config.IRegexContainer;
import app.model.entity.User;
import app.model.services.IUserService;

public class UserServiceImpl implements IUserService {
    private static
    DaoFactory daoFactory = DaoFactory.getInstance();
    @Override
    public boolean exists(String login) {
        if (daoFactory.createUserDao().exists(login)){
            throw new RuntimeException("Login "+login+" already exists");
            }
        return false;
    }

    // TODO boolean method or throw exception
    @Override
    public User insert(User user) {
        try(IUserDao dao = daoFactory.createUserDao()) {
            String regexLogin = IRegexContainer.REGEX_LOGIN;
            String regPassword = IRegexContainer.REGEX_PASSWORD;
            if (user.getLogin().matches(regexLogin) && user.getPassword().matches(regPassword)) {
                return dao.insert(user);
            }
        }
        return null;
    }


    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public Iterable<User> getAll() {
        try(IUserDao dao = daoFactory.createUserDao()){
            return dao.getAll();
        }
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
    public User getByLogin(String login) {
        try( IUserDao dao = daoFactory.createUserDao()) {
            return dao.getByLogin(login);
        }
    }

    @Override
    public User getByLoginAndPassword(String login, String password) {
        try(IUserDao dao = daoFactory.createUserDao()) {
            return dao.getByLoginAndPassword(login, password);
        }
    }

}
