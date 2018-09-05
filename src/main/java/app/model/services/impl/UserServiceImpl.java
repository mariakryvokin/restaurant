package app.model.services.impl;

import app.model.dao.DaoFactory;
import app.model.dao.IUserDao;
import app.model.entity.User;
import app.model.services.IUserService;

public class UserServiceImpl implements IUserService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public boolean exists(String login) {
        try(IUserDao dao = daoFactory.createUserDao()) {
            return  (dao.exists(login));
        }
    }

    @Override
    public User insert(User user) {
        try(IUserDao dao = daoFactory.createUserDao()) {
            return dao.insert(user);
        }
    }

    @Override
    public boolean isStringCorrect(String stringToCheck, String reg){
        return stringToCheck.matches(reg);
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
