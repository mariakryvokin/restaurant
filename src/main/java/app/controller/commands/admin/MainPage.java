package app.controller.commands.admin;

import app.controller.commands.ICommand;

import javax.servlet.http.HttpServletRequest;

public class MainPage implements ICommand {
    @Override
    public String execute(HttpServletRequest req, String method) {
        if(method.equals("get")){
            return "/WEB-INF/admin/main.jsp";
        }
        if(method.equals("post")){
            return "/ADMIN/main";
        }
        return null;
    }
}
