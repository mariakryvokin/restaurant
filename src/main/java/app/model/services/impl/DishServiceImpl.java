package app.model.services.impl;

import app.model.dao.DaoFactory;
import app.model.dao.IDishDao;
import app.model.dao.impl.DishDaoImpl;
import app.model.entity.Dish;
import app.model.services.IDishService;


public class DishServiceImpl implements IDishService {
    DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public Dish insert(Dish entity) {
        return null;
    }

    @Override
    public Dish findById(int id) {
        try(IDishDao dao = daoFactory.createDishDao()) {
            return dao.findById(id);
        }
    }

    @Override
    public Iterable<Dish> getAll() {
        try( IDishDao dao = daoFactory.createDishDao()) {
            return dao.getAll();
        }
    }

    @Override
    public boolean update(Dish entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Iterable<Dish> findDishForPagination(int currentPage, int recordsPerPage, int category_id) {
        try( IDishDao dao = daoFactory.createDishDao()){
            return dao.findDishForPagination(currentPage,recordsPerPage,category_id);
        }
    }

    @Override
    public int countCategoryDishes(int category_id){
        try(IDishDao dao = daoFactory.createDishDao()) {
            return dao.countCategoryDishes(category_id);
        }
    }

    @Override
    public Dish findByName(String dishName, String language) {
        try(IDishDao dao = daoFactory.createDishDao()){
            return dao.findByName(dishName,language);
        }
    }
}
