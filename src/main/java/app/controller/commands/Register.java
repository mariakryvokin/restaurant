package app.controller.commands;

import app.model.entity.User;
import app.model.services.Services;

import javax.servlet.http.HttpServletRequest;

public class Register implements ICommand {
    @Override
    public String execute(HttpServletRequest req, String method) {
        if (method.equals("post")) {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            if (Services.USER_SERVICE.exists(login)){
                throw  new RuntimeException("user already exists");
            }
            if(Services.USER_SERVICE.insert(new User(login, password)) == null) {
               throw new RuntimeException("login must start with capital latter and contain 8-20 latin signs, name in accordance with language");
            }
            return "/login";
        }
        if(method.equals("get")){
            return "/register.jsp";
        }
        return null;
    }


}
