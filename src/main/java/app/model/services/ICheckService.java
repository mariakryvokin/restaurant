package app.model.services;

import app.model.entity.Check;

import java.util.List;

public interface ICheckService extends IGenericService<Check> {
    List<Check> getAllNotPaidByUserId(int userId);
}
