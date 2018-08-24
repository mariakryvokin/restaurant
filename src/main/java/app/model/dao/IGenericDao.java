package app.model.dao;


public interface IGenericDao<T> extends AutoCloseable {
    T insert (T entity);
    T findById(int id);
    Iterable<T> getAll();
    boolean update(T entity);
    boolean delete(int id);
    void close();

}
