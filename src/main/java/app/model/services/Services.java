package app.model.services;

import app.model.services.impl.*;

public class Services {
    public static final IDishService DISH_SERVICE = new DishServiceImpl();
    public static final IUserService USER_SERVICE = new UserServiceImpl();
    public static final ICategoryService CATEGORY_SERVICE = new CategoryServiceImpl();
    public static final IOrderHasDish ORDER_HAS_DISH_SERVICE = new OrderHasDishImpl();
    public static final IOrderService ORDER_SERVICE = new OrderServiceImpl();
    public static final ICheckService CHECK_SERVICE = new CheckServiceImpl();

}
