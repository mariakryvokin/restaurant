package app.controller.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.*;


public class SessionListener implements HttpSessionListener {
    private static final Logger logger = LogManager.getLogger(SessionListener.class);
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.info("start session ");
    }

    @Override
    public synchronized void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        Set<String> loggedUsers =(Set<String>) httpSessionEvent
                .getSession().getServletContext()
                .getAttribute("loggedUsers");
        String login = (String) httpSessionEvent.getSession()
                .getAttribute("login");
        loggedUsers.remove(login);
        httpSessionEvent.getSession().getServletContext().setAttribute("loggedUsers", loggedUsers);
        logger.info("end of session for "+ login);
    }
}
