package app.model.dao;

import app.model.entity.Check;

import java.util.List;

public interface ICheckDao extends IGenericDao<Check> {
    List<Check> getAllUnpaidByUserId(int userId);
}
