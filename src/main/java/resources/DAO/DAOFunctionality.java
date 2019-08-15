package resources.DAO;

import java.util.List;

public interface DAOFunctionality<T> {

    T add(T domain);

    List<T> loadAll();

    T update(Long id, T domain);

    Long delete(Long id);

}