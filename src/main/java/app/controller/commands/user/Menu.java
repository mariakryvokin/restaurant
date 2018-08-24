package app.controller.commands.user;

import app.controller.commands.ICommand;
import app.model.services.Services;
import javax.servlet.http.HttpServletRequest;

public class Menu implements ICommand {
    @Override
    public String execute(HttpServletRequest req, String method) {
        int recordsPerPage =1;
        int currentPage =0;
        if (method.equals("get")) {
            int category_id = Integer.parseInt(req.getParameter("category_id"));
            req.setAttribute("category_id",category_id);
            if(req.getParameter("currentPage")!=null){
                currentPage = Integer.valueOf(req.getParameter("currentPage"));
            }
            req.setAttribute("dishes", Services.DISH_SERVICE.findDishForPagination(currentPage, recordsPerPage,category_id
            ));

            int rows = Services.DISH_SERVICE.countCategoryDishes(category_id);

            int nOfPages = rows / recordsPerPage;

            if (nOfPages % recordsPerPage > 0) {
                nOfPages++;
            }
            req.setAttribute("noOfPages", nOfPages);
            req.setAttribute("currentPage", currentPage);
            req.setAttribute("recordsPerPage", recordsPerPage);
            return  "/WEB-INF/user/menu.jsp";
        }
        if (method.equals("post")){
            return  "/USER/menu";
        }
        return  null;
    }
}
