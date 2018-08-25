package app.controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter( "/ADMIN/*")
public class AdminRightsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("admin righs FILTER");
        final HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        if(!session.getAttribute("role").toString().equals("ADMIN")){
            throw new RuntimeException("not enough right for this");
        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
