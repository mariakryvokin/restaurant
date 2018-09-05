package app.controller;

import app.controller.commands.*;
import app.controller.commands.admin.*;
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
        commands.put("/user/main", new app.controller.commands.user.MainPage());
        commands.put("/user/showCheck", new ShowCheck());
        commands.put("/user/addToOrder", new AddToOrder());
        commands.put("/admin/editOrder", new EditOrder());
        commands.put("/user/showOrder", new ShowOrder());
        commands.put("/user/deleteItemFromOrder", new DeleteFromOrder());
        commands.put("/user/menu", new Menu());
        commands.put("/user/category", new Category());
        commands.put("/user/sendOrder", new SendOrder());
        commands.put("/user/showAllSendedOrders", new ShowAllSendedOrders());
        commands.put("/user/payCheck", new PayCheck());
        commands.put("/admin/main", new app.controller.commands.admin.MainPage());
        commands.put("/admin/setDeletedStatus", new OrderSetDeletedStatus());
        commands.put("/admin/ordersToConfirm", new OrderToConfirm());
        commands.put("/admin/ordersForUser",new OrdersForUser());
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
        ICommand command = commands.getOrDefault(path , (r,t)->"index.jsp");
        String page = command.execute(req,req.getMethod().toLowerCase());
        if(req.getMethod().toLowerCase().equals("post")){
            resp.sendRedirect(page);
            return;
        }
        req.getRequestDispatcher(page).forward(req, resp);

    }
}
