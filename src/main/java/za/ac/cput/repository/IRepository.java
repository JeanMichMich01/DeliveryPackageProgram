package za.ac.cput.repository;

import java.sql.SQLException;

public interface IRepository<T, ID> {
    public T create(T t) throws SQLException;
    public T read(ID id) throws SQLException;
    public T update(T t) throws SQLException;
    public boolean delete(ID id) throws SQLException;

}
