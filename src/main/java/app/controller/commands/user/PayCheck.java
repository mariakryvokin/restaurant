package app.controller.commands.user;

import app.controller.commands.ICommand;
import app.model.services.Services;

import javax.servlet.http.HttpServletRequest;

public class PayCheck implements ICommand {
    @Override
    public String execute(HttpServletRequest req, String method) {
        if(method.equals("post")){
            System.out.println("check id "+ req.getParameter("checkId"));
            Services.CHECK_SERVICE.update(Services.CHECK_SERVICE.findById(Integer.parseInt(req.getParameter("checkId"))));
            return "/user/main";
        }
        return null;
    }
}
