package app.model.dao;

import app.model.entity.User;

public interface IUserDao extends IGenericDao<User> {
    boolean exists(String login);
    User getByLogin(String login);
    User getByLoginAndPassword(String login, String password);

}
