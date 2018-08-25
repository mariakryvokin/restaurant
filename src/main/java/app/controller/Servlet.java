package app.controller;

import app.controller.commands.*;
import app.controller.commands.admin.MainPage;
import app.controller.commands.admin.OrderToConfirm;
import app.controller.commands.user.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class Servlet extends HttpServlet {
    private Map<String, ICommand> commands = new HashMap<>();

    public void init(ServletConfig servletConfig){
        System.out.println("SERVLET INIT");
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", Collections.synchronizedSet(new HashSet<String>()));

        commands.put("/register", new Register());
        commands.put("/login", new Login());
        commands.put("/logout", new Logout());
        commands.put("/USER/main", new app.controller.commands.user.MainPage());
        commands.put("/ADMIN/main", new MainPage());
        commands.put("/USER/addToOrder", new AddToOrder());
        commands.put("/USER/showOrder", new ShowOrder());
        commands.put("/USER/menu", new Menu());
        commands.put("/USER/category", new Category());
        commands.put("/USER/sendOrder", new SendOrder());
        commands.put("/USER/deleteItemFromOrder", new DeleteFromOrder());
        commands.put("/ADMIN/ordersToConfirm", new OrderToConfirm());
        commands.put("/USER/showCheck", new ShowCheck());
        commands.put("/USER/payCheck", new PayCheck());
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        String path = req.getRequestURI();
        System.out.println("path "+ path);
        ICommand command = commands.getOrDefault(path , (r,t)->"index.jsp");
        String page = command.execute(req,req.getMethod().toLowerCase());
        if(req.getMethod().toLowerCase().equals("post")){
            resp.sendRedirect(page);
            return;
        }
        req.getRequestDispatcher(page).forward(req, resp);

    }
}
