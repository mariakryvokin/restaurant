package app.controller.commands.user;

import app.controller.commands.ICommand;
import app.model.services.Services;

import javax.servlet.http.HttpServletRequest;

public class ShowCheck implements ICommand {
    @Override
    public String execute(HttpServletRequest req, String method) {
        if(method.equals("post")){
            return "/USER/showCheck";
        }
        if(method.toLowerCase().equals("get")){
            System.out.println("SHOW CHECK Get");
            req.setAttribute("allChecks",
                    Services.CHECK_SERVICE.getAllNotPaidByUserId((Integer) req.getSession().getAttribute("userId")));
            System.out.println(req.getAttribute("allChecks").toString());
            return "/WEB-INF/user/showCheck.jsp";
        }
        return null;

    }
}
