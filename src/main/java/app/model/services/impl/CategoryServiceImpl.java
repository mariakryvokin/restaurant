package app.model.services.impl;

import app.model.dao.DaoFactory;
import app.model.dao.ICategoryDao;
import app.model.entity.Category;
import app.model.services.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    @Override
    public Category insert(Category entity) {
        return null;
    }

    @Override
    public Category findById(int id) {
        return null;
    }

    @Override
    public Iterable<Category> getAll() {
        try ( ICategoryDao dao = daoFactory.createCategoryDao()){
            return dao.getAll();
        }
    }

    @Override
    public boolean update(Category entity) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
