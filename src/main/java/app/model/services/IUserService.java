package app.model.services;

import app.model.entity.User;

public interface IUserService extends IGenericService<User> {
    boolean exists(String login);
    boolean isStringCorrect(String stringToCheck, String reg);
    User getByLogin(String login);
    User getByLoginAndPassword(String login, String password);
}
