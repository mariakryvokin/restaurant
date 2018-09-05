package app.model.dao;

import app.model.entity.Dish;

public interface IDishDao extends IGenericDao<Dish> {
    Iterable<Dish> findDishForPagination(int currentPage, int numOfRecords, int category_id);
    int countCategoryDishes(int category_id);
    Dish findByName(String dishName, String language);
}
