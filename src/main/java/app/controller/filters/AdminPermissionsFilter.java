package app.controller.filters;

import app.exceptions.NotEnoughPermissionException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter( "/admin/*")
public class AdminPermissionsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(true);
        if (session.getAttribute("role") == null || !session.getAttribute("role").toString().toLowerCase().equals("admin") ) {
            throw new NotEnoughPermissionException(403,"not enough permission");
        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
