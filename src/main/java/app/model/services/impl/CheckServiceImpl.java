package app.model.services.impl;

import app.model.dao.DaoFactory;
import app.model.dao.ICheckDao;
import app.model.entity.Check;
import app.model.services.ICheckService;

import java.util.List;

public class CheckServiceImpl implements ICheckService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    @Override
    public Check insert(Check entity) {
        try(ICheckDao checkDao = daoFactory.createCheckDao()) {
           return checkDao.insert(entity);
        }
    }

    @Override
    public Check findById(int id) {
        try(ICheckDao dao = daoFactory.createCheckDao()){
            return dao.findById(id);
        }
    }

    @Override
    public Iterable<Check> getAll() {
        return null;
    }

    @Override
    public boolean update(Check entity) {
        try(ICheckDao dao = daoFactory.createCheckDao()){
           return dao.update(entity);
        }
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Check> getAllUnpaidByUserId(int userId) {
        try(ICheckDao checkDao =  daoFactory.createCheckDao()) {
            return checkDao.getAllUnpaidByUserId(userId);
        }
    }
}
