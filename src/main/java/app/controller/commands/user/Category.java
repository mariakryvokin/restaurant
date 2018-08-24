package app.controller.commands.user;

import app.controller.commands.ICommand;
import app.model.services.Services;

import javax.servlet.http.HttpServletRequest;

public class Category implements ICommand {
    @Override
    public String execute(HttpServletRequest req, String method) {
        if(method.equals("post")){
            return "/USER/category";
        }
        if(method.equals("get")) {
            req.setAttribute("categories", Services.CATEGORY_SERVICE.getAll());
            return "/WEB-INF/user/category.jsp";
        }
        return null;
    }
}
