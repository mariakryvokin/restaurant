package app.controller.commands;

import app.controller.listener.SessionListener;
import app.model.entity.User;
import app.model.services.Services;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class CommandUtility {
    private static final Logger logger = LogManager.getLogger(SessionListener.class);
    static void connectSessionToUser(HttpServletRequest request, String login) {
        User user = Services.USER_SERVICE.getByLogin(login);
        request.getSession().setAttribute("login", login);
        request.getSession().setAttribute("userId",user.getId());
        request.getSession().setAttribute("role",user.getRole());
        logger.info("start of session for "+ login);
    }

    static boolean checkUserIsLogged(HttpServletRequest request, String login){
       /* HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");*/
        Set<String> loggedUsers = Collections.synchronizedSet((Set<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers"));
        if(loggedUsers.contains(login)){
            return true;
        }
        loggedUsers.add(login);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }
}
