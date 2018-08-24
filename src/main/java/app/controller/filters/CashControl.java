package app.controller.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
 /*@WebFilter(urlPatterns = { "/userMain/*" ,"/adminMain/*"})*/
    @WebFilter("/*")
    public class CashControl implements Filter {
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            final HttpServletRequest req = (HttpServletRequest) servletRequest;
            final HttpServletResponse resp = (HttpServletResponse) servletResponse;

            resp.setHeader("Cache-Control","no-cache");
            resp.setHeader("Cache-Control","no-store");
            resp.setDateHeader("Expires", 0);
            resp.setHeader("Pragma","no-cache");
            filterChain.doFilter(servletRequest,servletResponse);
        }

        @Override
        public void destroy() {

        }
}
