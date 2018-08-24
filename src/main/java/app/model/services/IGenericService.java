package app.model.services;


public interface IGenericService<T>  {
    T insert (T entity);
    T findById(int id);
    Iterable<T> getAll();
    boolean update(T entity);
    boolean delete(int id);
}