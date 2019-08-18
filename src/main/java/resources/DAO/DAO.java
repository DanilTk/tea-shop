package resources.DAO;

import java.util.List;

public interface DAO<T> {

    T add(T domain);

    List<T> getAll();

    T update(Integer id, T domain);

    Integer delete(Integer id);

}