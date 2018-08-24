package app.controller.commands.user;

import app.controller.commands.ICommand;

import javax.servlet.http.HttpServletRequest;

public class MainPage implements ICommand {
    @Override
    public String execute(HttpServletRequest req, String method) {
        if (method.equals("post")){
            return "/USER/main";
        }
        if(method.equals("get")){
            return "/WEB-INF/user/main.jsp";
        }
        return null;
    }
}
