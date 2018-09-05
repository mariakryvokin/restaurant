package app.controller.commands;

import app.model.entity.User;
import app.model.services.Services;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Set;

class CommandUtility {
    static void connectSessionToUser(HttpServletRequest request, String login) {
        User user = Services.USER_SERVICE.getByLogin(login);
        request.getSession().setAttribute("login", login);
        request.getSession().setAttribute("userId",user.getId());
        request.getSession().setAttribute("role",user.getRole());
    }

    static synchronized boolean checkUserIsLogged(HttpServletRequest request, String login){
       /* HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");*/
        Set<String> loggedUsers = (Set<String>) request.getSession().getServletContext().getAttribute("loggedUsers");
        if(loggedUsers.contains(login)){
            return true;
        }
        loggedUsers.add(login);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }
}
