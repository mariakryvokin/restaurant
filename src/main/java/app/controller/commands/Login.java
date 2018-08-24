package app.controller.commands;

import app.model.entity.User;
import app.model.services.Services;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class Login implements ICommand {

    @Override
    public String execute(HttpServletRequest req, String method) {
        if(method.equals("get")) {
            return "/login.jsp";
        }
        if(method.equals("post")){
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            if(login == null || login.equals("") || password == null || password.equals("")){
                throw new RuntimeException("please enter login and password");
            }
            User user = Services.USER_SERVICE.getByLoginAndPassword(login,password);
            if(user==null){
                throw new RuntimeException("user "+ login +" with such password doesn't exists");
            }
            if(CommandUtility.checkUserIsLogged(req, login)){
                throw new RuntimeException("Already logged");
            }
            CommandUtility.connectSessionToUser(req,login);
            if (user.getRole().toString().equals("ADMIN")){
                return "/ADMIN/main";
            }
                return "/USER/main";
        }
        return null;
    }

}
