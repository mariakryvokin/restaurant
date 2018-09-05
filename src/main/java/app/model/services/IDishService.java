package app.model.services;

import app.model.entity.Dish;

public interface IDishService extends IGenericService<Dish>{
    Iterable<Dish> findDishForPagination(int currentPage, int recordsPerPage, int category_id);
    int countCategoryDishes(int category_id);
    Dish findByName(String dishName, String language);
}
