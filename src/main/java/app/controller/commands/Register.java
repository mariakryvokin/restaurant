package app.controller.commands;

import app.config.IRegexContainer;
import app.exceptions.ExistsException;
import app.exceptions.HttpException;
import app.exceptions.WrongInputException;
import app.model.entity.User;
import app.model.services.Services;

import javax.servlet.http.HttpServletRequest;

public class Register implements ICommand {
    @Override
    public String execute(HttpServletRequest req, String method) throws HttpException {
        if (method.equals("post")) {
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            if (Services.USER_SERVICE.exists(login)){
                throw new ExistsException(400,"user already exists");
            }
            if(Services.USER_SERVICE.isStringCorrect(login,IRegexContainer.REGEX_LOGIN) &&
                    Services.USER_SERVICE.isStringCorrect(password,IRegexContainer.REGEX_PASSWORD)) {
                Services.USER_SERVICE.insert(new User(login,password));
            }else {
                throw new WrongInputException(400,"login must start with capital latter and contain 8-20 latin signs, name in accordance with language");
            }
            return "/login";
        }
        if(method.equals("get")){
            System.out.println("Register do get");
            return "/WEB-INF/register.jsp";
        }
        return null;
    }


}
